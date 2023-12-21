package be.vives.ti.dndweapons.controller;

import be.vives.ti.dndweapons.controllers.AttackController;
import be.vives.ti.dndweapons.controllers.WeaponController;
import be.vives.ti.dndweapons.domain.*;
import be.vives.ti.dndweapons.domain.enums.*;
import be.vives.ti.dndweapons.repository.AttackRepository;
import be.vives.ti.dndweapons.repository.WeaponAttackRepository;
import be.vives.ti.dndweapons.repository.WeaponRepository;
import be.vives.ti.dndweapons.requests.AttackRequest;
import be.vives.ti.dndweapons.requests.WeaponAttackRequest;
import be.vives.ti.dndweapons.requests.WeaponRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(WeaponController.class)
public class WeaponControllerTest {
    private final String baseUrl = "/weapons";

    @MockBean
    private AttackRepository attackRepository;
    @MockBean
    private WeaponAttackRepository weaponAttackRepository;
    @MockBean
    private WeaponRepository weaponRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Weapon> weapons;
    private List<WeaponAttack> weaponAttacks;

    @BeforeEach
    void setUp() {
        weapons = new ArrayList<>();
        weaponAttacks = new ArrayList<>();

        List<DamageRoll> damageRolls1 = new ArrayList<>();
        AttackRange range1 = new AttackRange(RangeType.MELEE, null, null);
        damageRolls1.add(new DamageRoll(1, 8, DamageType.SLASHING));
        weaponAttacks.add(new WeaponAttack("Longsword", damageRolls1, range1));

        List<DamageRoll> damageRolls2 = new ArrayList<>();
        damageRolls2.add(new DamageRoll(1, 6, DamageType.PIERCING));
        AttackRange range2 = new AttackRange(RangeType.RANGED, 80, 320);
        weaponAttacks.add(new WeaponAttack("Shortbow", damageRolls2, range2));

        List<DamageRoll> damageRolls3 = new ArrayList<>();
        damageRolls3.add(new DamageRoll(1, 10, DamageType.SLASHING));
        AttackRange range3 = new AttackRange(RangeType.MELEE, null, null);
        weaponAttacks.add(new WeaponAttack("Longsword (two-handed)", damageRolls3, range3));

        List<WeaponProperty> properties1 = new ArrayList<>();
        properties1.add(WeaponProperty.VERSATILE);
        Weapon weapon1 = new Weapon(
                "Longsword",
                new Cost(15, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                properties1,
                WeaponType.MELEE_WEAPON,
                true
        );

        List<WeaponProperty> properties2 = new ArrayList<>();
        properties2.add(WeaponProperty.AMMUNITION);
        properties2.add(WeaponProperty.TWO_HANDED);
        Weapon weapon2 = new Weapon(
                "Shortbow",
                new Cost(25, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                properties2,
                WeaponType.RANGED_WEAPON,
                false
        );

        weapon1.addAttack(weaponAttacks.get(0));
        weapon1.addAttack(weaponAttacks.get(2));
        weapon2.addAttack(weaponAttacks.get(1));

        weapons.add(weapon1);
        weapons.add(weapon2);
    }

    @Test
    void findAllWeapons() throws Exception {
        Page page = new PageImpl(weapons);
        PageRequest of = PageRequest.of(0, 20);
        when(weaponRepository.findAll(of)).thenReturn(page);

        mvc.perform(get(baseUrl))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name", equalTo("Longsword")))
                .andExpect(jsonPath("$.content[1].name", equalTo("Shortbow")));
    }

    @Test
    void findByNameIgnoringCase() throws Exception {
        // TODO
    }

    @Test
    void retrieveWeaponById() throws Exception {
        Weapon longsword = weapons.get(0);
        when(weaponRepository.findById(1L)).thenReturn(Optional.of(longsword));

        mvc.perform(get(baseUrl + "/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Longsword")))
                .andExpect(jsonPath("$.cost.amount", equalTo(15)))
                .andExpect(jsonPath("$.cost.coinType", equalTo("gp")))
                .andExpect(jsonPath("$.damageModifier", equalTo(0)))
                .andExpect(jsonPath("$.rarity", equalTo("Common")))
                .andExpect(jsonPath("$.weight", equalTo(3.0)))
                .andExpect(jsonPath("$.properties", hasSize(1)))
                .andExpect(jsonPath("$.properties[0]", equalTo("Versatile")))
                .andExpect(jsonPath("$.weaponType", equalTo("Melee weapon")))
                .andExpect(jsonPath("$.isMartial", equalTo(true)));
    }

    @Test
    void retrieveByIdNotFound() throws Exception {
        when(weaponRepository.findById(100L)).thenReturn(Optional.empty());
        mvc.perform(get(baseUrl+"/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void retrieveWeaponByIdWithProperties() throws Exception {
        Weapon longsword = weapons.get(0);
        when(weaponRepository.findById(1L)).thenReturn(Optional.of(longsword));

        mvc.perform(get(baseUrl + "/1/properties"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Longsword")))
                .andExpect(jsonPath("$.rarity", equalTo("Common")))
                .andExpect(jsonPath("$.properties", hasSize(1)))
                .andExpect(jsonPath("$.properties[0].name", equalTo("Versatile")))
                .andExpect(jsonPath("$.properties[0].description", equalTo(WeaponProperty.VERSATILE.getDescription())))
                .andExpect(jsonPath("$.weaponType", equalTo("Melee weapon")));
    }

    @Test
    void retrieveWeaponByIdWithPropertiesNotFound() throws Exception {
        when(weaponRepository.findById(100L)).thenReturn(Optional.empty());
        mvc.perform(get(baseUrl+"/100/properties"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void createWeapon() throws Exception {
        Weapon longsword = weapons.get(0);
        longsword.setId(1L);
        when(weaponRepository.save(any(Weapon.class))).thenReturn(longsword);

        WeaponRequest request = new WeaponRequest();
        request.setName("Longsword");
        request.setCost(new Cost(15, CoinType.GP));
        request.setRarity(Rarity.COMMON);
        request.setDamageModifier(0);
        request.setWeight(3.0);
        request.setProperties(List.of(WeaponProperty.VERSATILE));
        request.setWeaponType(WeaponType.MELEE_WEAPON);
        request.setMartial(true);

        mvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "http://localhost/weapons/1"));
    }

    @Test
    void createWeaponWithValidationError() throws Exception {
        WeaponRequest request = new WeaponRequest();
        request.setName("");    // lege naam
        request.setCost(new Cost(15, CoinType.GP));
        request.setRarity(Rarity.COMMON);
        request.setDamageModifier(0);
        request.setWeight(3.0);
        request.setProperties(List.of(WeaponProperty.VERSATILE));
        request.setWeaponType(WeaponType.MELEE_WEAPON);
        request.setMartial(true);

        mvc.perform(post(baseUrl)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(attackRepository);
    }

    @Test
    void updateWeapon() throws Exception {
        Long id = 1L;

        Weapon longsword = weapons.get(0);
        longsword.setId(id);
        when(weaponRepository.findById(id)).thenReturn(Optional.of(longsword));
        when(weaponRepository.save(any(Weapon.class))).thenAnswer(i -> i.getArguments()[0]);

        WeaponRequest request = new WeaponRequest();
        request.setName("Longsword");
        request.setCost(new Cost(15, CoinType.GP));
        request.setRarity(Rarity.COMMON);
        request.setDamageModifier(0);
        request.setWeight(3.0);
        request.setProperties(List.of(WeaponProperty.VERSATILE));
        request.setWeaponType(WeaponType.MELEE_WEAPON);
        request.setMartial(true);

        mvc.perform(put(baseUrl+"/"+id)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateWeaponNotFound() throws Exception {
        Long id = 1L;
        when(weaponRepository.findById(id)).thenReturn(Optional.empty());

        verifyNoMoreInteractions(weaponRepository);

        WeaponRequest request = new WeaponRequest();
        request.setName("Longsword");
        request.setCost(new Cost(15, CoinType.GP));
        request.setRarity(Rarity.COMMON);
        request.setDamageModifier(0);
        request.setWeight(3.0);
        request.setProperties(List.of(WeaponProperty.VERSATILE));
        request.setWeaponType(WeaponType.MELEE_WEAPON);
        request.setMartial(true);

        mvc.perform(put(baseUrl+"/"+id)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteWeapon() throws Exception {
        Long id = 1L;

        Weapon longsword = weapons.get(0);
        longsword.setId(id);
        when(weaponRepository.findById(id)).thenReturn(Optional.of(longsword));

        mvc.perform(delete(baseUrl+"/"+id))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteWeaponNotFound() throws Exception {
        Long id = 1L;

        when(weaponRepository.findById(id)).thenReturn(Optional.empty());

        mvc.perform(delete(baseUrl + "/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void addAttackToWeapon() throws Exception {
        WeaponAttack attack = weaponAttacks.get(0);
        attack.setId(1L);
        Weapon longsword = weapons.get(0);
        longsword.setId(1L);
        when(weaponRepository.findById(1L)).thenReturn(Optional.of(longsword));
        when(weaponAttackRepository.save(any(WeaponAttack.class))).thenReturn(attack);

        List<DamageRoll> damageRolls = List.of(new DamageRoll(1, 8, DamageType.SLASHING));
        AttackRange range = new AttackRange(RangeType.MELEE, null, null);
        WeaponAttackRequest request = new WeaponAttackRequest();
        request.setName("Longsword");
        request.setDamageRolls(damageRolls);
        request.setRange(range);

        mvc.perform(post(baseUrl + "/1/weapon-attacks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "http://localhost/weapons/1/weapon-attacks"));
    }

    @Test
    void addAttackToWeaponNotFound() throws Exception {
        when(weaponAttackRepository.findById(100L)).thenReturn(Optional.empty());

        mvc.perform(delete("/100/weapon-attacks"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addAttackToWeaponWithValidationError() throws Exception {
        WeaponAttack attack = weaponAttacks.get(0);
        Weapon longsword = weapons.get(0);
        when(weaponRepository.findById(1L)).thenReturn(Optional.of(longsword));
        when(weaponAttackRepository.save(any(WeaponAttack.class))).thenReturn(attack);

        List<DamageRoll> damageRolls = List.of(new DamageRoll(1, 8, DamageType.SLASHING));
        AttackRange range = new AttackRange(RangeType.MELEE, null, null);
        WeaponAttackRequest request = new WeaponAttackRequest();
        request.setName(""); // lege naam
        request.setDamageRolls(damageRolls);
        request.setRange(range);

        mvc.perform(post(baseUrl + "/1/weapon-attacks")
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(attackRepository);
    }

    @Test
    void deleteAttackOfWeapon() throws Exception {
        Long id = 1L;

        WeaponAttack attack = weaponAttacks.get(0);
        attack.setId(id);
        when(weaponAttackRepository.findById(id)).thenReturn(Optional.of(attack));

        mvc.perform(delete(baseUrl + "/100/weapon-attacks/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAttackOfWeaponNotFound() throws Exception {
        when(weaponAttackRepository.findById(100L)).thenReturn(Optional.empty());

        mvc.perform(delete("/100/weapon-attacks/100"))
                .andExpect(status().isNotFound());
    }
}

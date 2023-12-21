package be.vives.ti.dndweapons.controller;

import be.vives.ti.dndweapons.controllers.AttackController;
import be.vives.ti.dndweapons.domain.Attack;
import be.vives.ti.dndweapons.domain.AttackRange;
import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.domain.TestDomain;
import be.vives.ti.dndweapons.domain.enums.AbilityType;
import be.vives.ti.dndweapons.domain.enums.DamageType;
import be.vives.ti.dndweapons.domain.enums.RangeType;
import be.vives.ti.dndweapons.repository.AttackRepository;
import be.vives.ti.dndweapons.repository.TestRepository;
import be.vives.ti.dndweapons.repository.WeaponAttackRepository;
import be.vives.ti.dndweapons.repository.WeaponRepository;
import be.vives.ti.dndweapons.requests.AttackRequest;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AttackController.class)
public class AttackControllerTest {
    private final String baseUrl = "/attacks";

    @MockBean
    private AttackRepository attackRepository;
    @MockBean
    private WeaponAttackRepository weaponAttackRepository;
    @MockBean
    private WeaponRepository weaponRepository;
    //VERWIJDEREN
    @MockBean
    private TestRepository testRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Attack> attacks;

    @BeforeEach
    void setUp() {
        attacks = new ArrayList<>();

        List<DamageRoll> damageRolls1 = new ArrayList<>();
        AttackRange range1 = new AttackRange(RangeType.MELEE, null, null);
        damageRolls1.add(new DamageRoll(1, 8, DamageType.SLASHING));
        Attack attack1 = new Attack("Longsword", 0, AbilityType.STRENGTH , damageRolls1, range1);
        attack1.setId(1L);
        attacks.add(attack1);

        List<DamageRoll> damageRolls2 = new ArrayList<>();
        damageRolls2.add(new DamageRoll(1, 6, DamageType.PIERCING));
        AttackRange range2 = new AttackRange(RangeType.RANGED, 80, 320);
        Attack attack2 = new Attack("Shortbow", 0, AbilityType.DEXTERITY , damageRolls2, range2);
        attack2.setId(2L);
        attacks.add(attack2);

        List<DamageRoll> damageRolls3 = new ArrayList<>();
        damageRolls3.add(new DamageRoll(1, 10, DamageType.SLASHING));
        AttackRange range3 = new AttackRange(RangeType.MELEE, null, null);
        Attack attack3 = new Attack("Longsword (two-handed)", 0, AbilityType.STRENGTH , damageRolls3, range3);
        attack3.setId(3L);
        attacks.add(attack3);

        System.setProperty("logging.level.org.springframework.web", "DEBUG");
    }

    @Test
    void findAllAttacks() throws Exception {
        Page page = new PageImpl(attacks);
        PageRequest of = PageRequest.of(0, 20);
        when(attackRepository.findAll(of)).thenReturn(page);

        mvc.perform(get(baseUrl))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[0].name", equalTo("Longsword")))
                .andExpect(jsonPath("$.content[1].name", equalTo("Shortbow")))
                .andExpect(jsonPath("$.content[2].name", equalTo("Longsword (two-handed)")));
    }

    // VERWIJDEREN
    @Test
    void findAllAttacksTest() throws Exception {
        when(attackRepository.findAll()).thenReturn(attacks);

        mvc.perform(get(baseUrl + "/test"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[0].name", equalTo("Longsword")))
                .andExpect(jsonPath("$.content[1].name", equalTo("Shortbow")))
                .andExpect(jsonPath("$.content[2].name", equalTo("Longsword (two-handed)")));
    }

    //VERWIJDEREN
    @Test
    void findAllTests() throws Exception {
        ArrayList<TestDomain> tests = new ArrayList<>();
        tests.add(new TestDomain("TEST 1"));
        tests.add(new TestDomain("TEST 2"));
        Page page = new PageImpl(tests);
        PageRequest of = PageRequest.of(0, 20);
        when(testRepository.findAll(of)).thenReturn(page);

        mvc.perform(get(baseUrl+"/pageTest"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findById() throws Exception {
        Attack longsword = attacks.get(0);
        when(attackRepository.findById(1L)).thenReturn(Optional.of(longsword));
        mvc.perform(get(baseUrl + "/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Longsword")))
                .andExpect(jsonPath("$.damageModifier", equalTo(0)))
                .andExpect(jsonPath("$.abilityType", equalTo("Strength")))
                .andExpect(jsonPath("$.damageRolls", hasSize(1)))
                .andExpect(jsonPath("$.damageRolls[0].amount", equalTo(1)))
                .andExpect(jsonPath("$.damageRolls[0].dieType", equalTo(8)))
                .andExpect(jsonPath("$.damageRolls[0].damageType", equalTo("Slashing")))
                .andExpect(jsonPath("$.range.rangeType", equalTo("Melee")))
                .andExpect(jsonPath("$.range.normalRange", equalTo(null)))
                .andExpect(jsonPath("$.range.longRange", equalTo(null)));
    }

    @Test
    void findByIdNotFound() throws Exception {
        when(attackRepository.findById(100L)).thenReturn(Optional.empty());
        mvc.perform(get(baseUrl+"/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void createAttack() throws Exception {
        List<DamageRoll> damageRolls = new ArrayList<>();
        damageRolls.add(new DamageRoll(1, 8, DamageType.PIERCING));
        AttackRange range = new AttackRange(RangeType.RANGED, 150, 600);
        Attack longbow = new Attack("Longbow", 0, AbilityType.DEXTERITY , damageRolls, range);
        longbow.setId(1L);
        when(attackRepository.save(any(Attack.class))).thenReturn(longbow);

        AttackRequest request = new AttackRequest();
        request.setName("Longbow");
        request.setDamageModifier(0);
        request.setAbilityType(AbilityType.DEXTERITY);
        request.setDamageRolls(damageRolls);
        request.setRange(range);

        mvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "http://localhost/attacks/1"));
    }

    @Test
    void createAttackWithValidationError() throws Exception {
        AttackRange range = new AttackRange(RangeType.RANGED, 150, 600);
        AttackRequest request = new AttackRequest();
        request.setName("Longbow");
        request.setDamageModifier(0);
        request.setAbilityType(AbilityType.DEXTERITY);
        request.setDamageRolls(new ArrayList<>());  // lege lijst
        request.setRange(range);

        mvc.perform(post(baseUrl)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(attackRepository);
    }

    @Test
    void updateAttack() throws Exception {
        Long id = 1L;

        List<DamageRoll> damageRolls = new ArrayList<>();
        damageRolls.add(new DamageRoll(1, 8, DamageType.PIERCING));
        AttackRange range = new AttackRange(RangeType.RANGED, 150, 600);
        Attack longbow = new Attack("Longbow", 0, AbilityType.DEXTERITY , damageRolls, range);
        longbow.setId(id);
        when(attackRepository.findById(id)).thenReturn(Optional.of(longbow));
        when(attackRepository.save(any(Attack.class))).thenAnswer(i -> i.getArguments()[0]);

        AttackRequest request = new AttackRequest();
        request.setName("Longbow");
        request.setDamageModifier(0);
        request.setAbilityType(AbilityType.DEXTERITY);
        request.setDamageRolls(damageRolls);
        request.setRange(range);

        mvc.perform(put(baseUrl+"/"+id)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateAttackNotFound() throws Exception {
        Long id = 1L;
        when(attackRepository.findById(id)).thenReturn(Optional.empty());

        verifyNoMoreInteractions(attackRepository);

        List<DamageRoll> damageRolls = new ArrayList<>();
        damageRolls.add(new DamageRoll(1, 8, DamageType.PIERCING));
        AttackRange range = new AttackRange(RangeType.RANGED, 150, 600);
        AttackRequest request = new AttackRequest();
        request.setName("Longbow");
        request.setDamageModifier(0);
        request.setAbilityType(AbilityType.DEXTERITY);
        request.setDamageRolls(damageRolls);
        request.setRange(range);

        mvc.perform(put(baseUrl+"/"+id)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateAttackWithValidationErrors() throws Exception {
        AttackRequest request = new AttackRequest();
        request.setName("Longbow");

        mvc.perform(put(baseUrl+"/"+1L)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(attackRepository);
    }

    @Test
    void deleteAttack() throws Exception {
        Long id = 1L;

        List<DamageRoll> damageRolls = new ArrayList<>();
        damageRolls.add(new DamageRoll(1, 8, DamageType.PIERCING));
        AttackRange range = new AttackRange(RangeType.RANGED, 150, 600);
        Attack longbow = new Attack("Longbow", 0, AbilityType.DEXTERITY , damageRolls, range);
        longbow.setId(id);
        when(attackRepository.findById(id)).thenReturn(Optional.of(longbow));

        mvc.perform(delete(baseUrl+"/"+id))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAttackNotFound() throws Exception {
        Long id = 1L;

        when(attackRepository.findById(id)).thenReturn(Optional.empty());

        mvc.perform(delete(baseUrl+"/"+id))
                .andExpect(status().isNotFound());
    }
}

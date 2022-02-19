package tourGuide.rewards.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.con.DTO.*;
import com.con.Entity.*;
import tourGuide.rewards.Service.RewardsService;
import tourGuide.rewards.TestUtils.Converter;
import tourGuide.rewards.TestUtils.Data;
import tourGuide.rewards.Utils.Mapper;

import java.util.ArrayList;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RewardsController.class)
class RewardsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RewardsService rewardsService;

    @Test
    void calculateRewardsTest() throws Exception {
        //Given
        User user = Data.getAUser();
        UserDTO userDTO = Mapper.convertUserToUserDTO(user);
        //When
        Mockito.when(rewardsService.calculateRewards(user)).thenReturn(new ArrayList<>());
        //Then
        mockMvc.perform(post("/rewards/calculateRewards").contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(userDTO))).andExpect(status().isOk());
    }
}

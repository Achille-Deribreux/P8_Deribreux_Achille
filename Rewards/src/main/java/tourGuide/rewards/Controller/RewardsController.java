package tourGuide.rewards.Controller;

import com.con.DTO.UserDTO;
import com.con.Entity.User;
import com.con.Entity.UserReward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.rewards.Service.RewardsService;
import tourGuide.rewards.Utils.Mapper;

import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    private final Logger logger = LoggerFactory.getLogger(RewardsController.class);

    @Autowired
    private RewardsService rewardsService;

    /**
     * This method answer to a post request at /calculateRewards and returns a list of rewards for a given user
     * @param userDTO user for which you want rewards
     * @return a list of the rewards of the user
     */
    @PostMapping("/calculateRewards")
    public List<UserReward> calculateRewards(@RequestBody UserDTO userDTO){
        logger.info("request at calculateRewards for user "+userDTO.getUserName());
        User user = Mapper.convertUserDTOtoUser(userDTO);
        return rewardsService.calculateRewards(user);
    }
}

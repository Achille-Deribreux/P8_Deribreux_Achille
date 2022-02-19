package tourGuide.pricer.Controller;

import com.con.Entity.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tourGuide.pricer.Service.PricerService;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pricer")
public class PricerController {

    @Autowired
    private PricerService pricerService;

    private final Logger logger = LoggerFactory.getLogger(PricerController.class);

    /**
     * This method answer to a get request at /getTripDeals
     * @param attractionId id of the attraction
     * @param username username of the user
     * @param adults number of adults
     * @param children number of childrends
     * @param nightsStay nights that the user prefer to stay
     * @param rewardsPoints total of the user's rewards points
     * @return a list of the generated tripdeals for this user
     */
    @GetMapping("/getTripDeals")
    public List<Provider> getTripDeals(
            @RequestParam(value="attractionId") UUID attractionId,
            @RequestParam(value="userName") String username,
            @RequestParam(value="adults")int adults,
            @RequestParam(value="children")int children,
            @RequestParam(value="nightsStay")int nightsStay,
            @RequestParam(value="rewardsPoints")int rewardsPoints
            ) throws URISyntaxException {
        logger.info("request at /getTripDeals");
        return pricerService.getTripDeals(username,attractionId,adults,children,nightsStay,rewardsPoints);
    }
}

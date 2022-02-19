package tourGuide.service;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tourGuide.Entity.User;
import tourGuide.WebClient.UserWebClient;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.*;

@Service
@EnableScheduling
public class TrackerService {

    @Autowired
    private UserWebClient userWebClient;

    @Autowired
    private TourGuideService tourGuideService;

    private Logger logger = LoggerFactory.getLogger(TrackerService.class);

    private List<User> users = null;


    /**
     * This method tracks the users, calculate rewards and generate trip deals, automatically every 30 minutes
     */
    @Scheduled(initialDelay = 5000L, fixedRate = 1800000L)
    public void trackerLocation() {
        if(users == null){
            users=userWebClient.getAllUsers();
        }
        logger.debug("start tracking " + users.size() + " users");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Create an executor service with a thread pool of certain amount of threads
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(44);

            //Execute the code as per in the method "trackListUserLocations" in TourGuideService
            //but without the calculation of rewards
            for (User user : users) {
                Runnable runnable = () -> {
                    try {
                        tourGuideService.trackUserLocation(user);
                        tourGuideService.calculateRewards(user);
                        tourGuideService.generateTripDeals(user);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                };
                executorService.execute(runnable);
            }
            executorService.shutdown();
            executorService.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException ignored) {
        }
        stopWatch.stop();

        //Asserting part that the time is as performant as wanted
        logger.debug("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
        };
}

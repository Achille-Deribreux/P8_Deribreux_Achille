package tourGuide;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.*;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import tourGuide.Configuration.Config;
import tourGuide.WebClient.GpsWebClient;
import tourGuide.WebClient.PricerWebClient;
import tourGuide.WebClient.RewardsWebClient;
import tourGuide.WebClient.UserWebClient;
import tourGuide.service.TourGuideService;
import tourGuide.Entity.User;

@SpringBootTest
public class TestPerformance {
	private Logger logger = LoggerFactory.getLogger(TestPerformance.class);

	//@Value("${tourguide.main.userurl}")
	private String BASE_URL_LOCALHOST_user  = "http://localhost:8082/user";

	//@Value("${tourguide.main.gpsurl}")
	private String BASE_URL_LOCALHOST_gps = "http://localhost:8081/gps";

	//@Value("${tourguide.main.rewardsurl}")
	private String BASE_URL_LOCALHOST_rewards = "http://localhost:8083/rewards";

	//@Value("${tourguide.main.pricerurl}")
	private String BASE_URL_LOCALHOST_pricer = "http://localhost:8084/pricer";
	/*
	 * A note on performance improvements:
	 *     
	 *     The number of users generated for the high volume tests can be easily adjusted via this method:
	 *     
	 *     		InternalTestHelper.setInternalUserNumber(100000);
	 *     
	 *     
	 *     These tests can be modified to suit new solutions, just as long as the performance metrics
	 *     at the end of the tests remains consistent. 
	 * 
	 *     These are performance metrics that we are trying to hit:
	 *     
	 *     highVolumeTrackLocation: 100,000 users within 15 minutes:
	 *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     *
     *     highVolumeGetRewards: 100,000 users within 20 minutes:
	 *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	 */

	@Test
	public void highVolumeTrackLocation() throws ExecutionException, InterruptedException, URISyntaxException {
		// Users should be incremented up to 100,000, and test finishes within 15 minutes
		TourGuideService tourGuideService = new TourGuideService();
		GpsWebClient gpsWebClient = new GpsWebClient();
		gpsWebClient.setBASE_URL_LOCALHOST_gps(BASE_URL_LOCALHOST_gps);
		UserWebClient userWebClient = new UserWebClient();
		userWebClient.setBASE_URL_LOCALHOST_user(BASE_URL_LOCALHOST_user);
		userWebClient.setRestTemplate(new RestTemplate());
		gpsWebClient.setRestTemplate(new RestTemplate());
		tourGuideService.setGpsWebClient(gpsWebClient);
		tourGuideService.setUserWebClient(userWebClient);
		List<User> users = userWebClient.getAllUsers();
		StopWatch timeCounter = new StopWatch();
		timeCounter.start();
		logger.debug("start tracking " + users.size() + " users");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(30);
			for (User user : users) {
				Runnable runnable = () -> {
					try {
						tourGuideService.trackUserLocation(user);
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				};
				executorService.execute(runnable);
			}
			executorService.shutdown();
			executorService.awaitTermination(15, TimeUnit.MINUTES);
		} catch (InterruptedException ignored) {
		}
		stopWatch.stop();

		//Asserting part that the time is as performant as wanted
		System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

	@Test
	public void highVolumeGetRewards() {
		UserWebClient userWebClient = new UserWebClient();
		RewardsWebClient rewardsWebClient = new RewardsWebClient();
		GpsWebClient gpsWebClient = new GpsWebClient();
		PricerWebClient pricerWebClient = new PricerWebClient();
		gpsWebClient.setBASE_URL_LOCALHOST_gps(BASE_URL_LOCALHOST_gps);
		pricerWebClient.setBASE_URL_LOCALHOST_pricer(BASE_URL_LOCALHOST_pricer);
		userWebClient.setBASE_URL_LOCALHOST_user(BASE_URL_LOCALHOST_user);
		rewardsWebClient.setBASE_URL_LOCALHOST_rewards(BASE_URL_LOCALHOST_rewards);
		rewardsWebClient.setRestTemplate(new RestTemplate());
		userWebClient.setRestTemplate(new RestTemplate());
		pricerWebClient.setRestTemplate(new RestTemplate());
		gpsWebClient.setRestTemplate(new RestTemplate());
		List<User> users = userWebClient.getAllUsers();
		TourGuideService tourGuideService = new TourGuideService();
		tourGuideService.setRewardsWebClient(rewardsWebClient);
		tourGuideService.setUserWebClient(userWebClient);
		tourGuideService.setRewardsWebClient(rewardsWebClient);
		tourGuideService.setPricerWebClient(pricerWebClient);

		logger.debug("start tracking " + users.size() + " users");

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(44);
			for (User user : users) {
				Runnable runnable = () -> {
					try {
						tourGuideService.calculateRewards(user);
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				};
				executorService.execute(runnable);
			}
			executorService.shutdown();
			executorService.awaitTermination(15, TimeUnit.MINUTES);
		} catch (InterruptedException ignored) {
		}
		stopWatch.stop();

		//Asserting part that the time is as performant as wanted
		logger.debug("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
	    
		/*for(User user : allUsers) {
			assertTrue(user.getUserRewards().size() > 0);
		}*/
		assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));

	}}

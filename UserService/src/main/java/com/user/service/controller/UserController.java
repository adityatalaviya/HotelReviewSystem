    package com.user.service.controller;

    import com.user.service.entity.User;
    import com.user.service.service.UserService;
    import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
    import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
    import io.github.resilience4j.retry.annotation.Retry;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController {

        @Autowired
        private UserService userService;

        private final Logger logger = LoggerFactory.getLogger(UserController.class);


        //create
        @PostMapping
        public ResponseEntity<User> creteUser(@RequestBody User user){
            User user1 = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        }

        //int retryCount=1;

        //getone
       // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
        //@Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
        @GetMapping("/{userId}")
        @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
        public ResponseEntity<User> getSingleUser(@PathVariable String userId){
            //logger.info("Retry count is:{}",retryCount);
           // retryCount++;
            User user = userService.getUser(userId);
            return ResponseEntity.ok(user);
        }


        //creating fallback methods circuit breaker
        public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
            //logger.info("Fallback is executed because service is down: {}", ex.getMessage());

            ex.printStackTrace();

            User user = User.builder()
                    .email("dummy@gmail.com")
                    .name("dummy")
                    .about("This user created dummy")
                    .userId("1254214")
                    .build();
            //return new ResponseEntity<>(user , HttpStatus.OK);
            return new ResponseEntity<>(user , HttpStatus.TOO_MANY_REQUESTS);
        }

        //get All
        @GetMapping
        public  ResponseEntity<List<User>> getAllUser(){
            List<User> allUser = userService.getAllUser();
            return ResponseEntity.ok(allUser);
        }

        //update
        @PutMapping("/{userId}")
        public ResponseEntity<User> updateUser(@PathVariable String userId,@RequestBody User user){
            User user1 = userService.updateUser(userId, user);
            return  ResponseEntity.ok(user1);
        }


        //delete
        @DeleteMapping("/{userId}")
        public ResponseEntity<String> deleteUser(@PathVariable String userId){
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted");
        }


    }

package signalement.app.Models;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserTokenRepository extends MongoRepository<UserToken,Integer> {
    @Query("{token : ?0}")                                         
    UserToken findByToken(String token);
}

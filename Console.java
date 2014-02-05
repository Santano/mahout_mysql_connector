package console;
 
 import java.io.IOException;
 
 import org.apache.mahout.cf.taste.common.TasteException;
 import org.apache.mahout.cf.taste.recommender.Recommender;
 import recommender.UserActivityRecommender;
 
 public class Console {
 	public static void main(String[] args) throws TasteException, IOException {
 		Recommender recommender = new UserActivityRecommender();
 		System.out.print(recommender.getDataModel().getPreferencesFromUser(1));
 	}
}

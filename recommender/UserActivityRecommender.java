package recommender;

import helper.DataSourceProvider;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

public class UserActivityRecommender implements Recommender {

	private Recommender _recommender;

	public UserActivityRecommender() throws TasteException, IOException {
		DataSourceProvider dataSourceProvider = DataSourceProvider
				.getInstance();
		DataModel dataModel = dataSourceProvider.getUserActivityModel();
		TanimotoCoefficientSimilarity itemSimilarity = new TanimotoCoefficientSimilarity(
				dataSourceProvider.getUserActivityModel());
		_recommender = new GenericBooleanPrefItemBasedRecommender(dataModel,
				itemSimilarity);
	}

	@Override
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		_recommender.refresh(alreadyRefreshed);
	}

	@Override
	public float estimatePreference(long userID, long itemID) throws TasteException {
		return _recommender.estimatePreference(userID, itemID);
	}

	@Override
	public DataModel getDataModel() {
		return _recommender.getDataModel();
	}

	@Override
	public List<RecommendedItem> recommend(long userID, int howMany)
			throws TasteException {
		return _recommender.recommend(userID, howMany);
	}

	@Override
	public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer)
			throws TasteException {
		return _recommender.recommend(userID, howMany, rescorer);
	}

	@Override
	public void removePreference(long userID, long itemID) throws TasteException {
		_recommender.removePreference(userID, itemID);
	}

	@Override
	public void setPreference(long userID, long itemID, float value)
			throws TasteException {
		_recommender.setPreference(userID, itemID, value);
	}
}

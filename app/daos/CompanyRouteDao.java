package daos;

import models.CompanyRouteModel;
import org.mongodb.morphia.Datastore;

import java.util.Iterator;

public class CompanyRouteDao {
    private static CompanyRouteDao companyRouteDao;
    private Datastore datastore;

    private CompanyRouteDao() {
        datastore = MongoDbDao.getInstance().getDatastore();
    }

    public static CompanyRouteDao getInstance() {
        if (companyRouteDao == null) {
            companyRouteDao = new CompanyRouteDao();
        }
        return companyRouteDao;
    }

    public boolean createCompanyRoute(CompanyRouteModel model) {
        try {
            datastore.save(model);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public CompanyRouteModel getCompanyRoute(String id) {
        try {
            Iterator<CompanyRouteModel> iterator = datastore.createQuery(CompanyRouteModel.class)
                    .field("objectId")
                    .equal(id)
                    .iterator();
            if (iterator.hasNext()) {
                return iterator.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

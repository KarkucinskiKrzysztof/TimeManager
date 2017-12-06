package pl.Time.Manager.database.dao;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.Time.Manager.database.dbuitls.DbManager;
import pl.Time.Manager.database.models.BaseModel;
import pl.Time.Manager.utils.FxmlUtils;
import pl.Time.Manager.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public abstract class CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DbManager.getConnectionSource();
    }


    public <T extends BaseModel, I> void creatOrUpdate(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {

            LOGGER.warn(e.getCause().getMessage());  // łapiemy dokładny wyjątek
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.create.update"));
        } finally {
            this.closeDbConnecton();
        }
    }


    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        } finally {
            this.closeDbConnecton();
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found"));
        } finally {
            this.closeDbConnecton();
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found.all"));
        } finally {
            this.closeDbConnecton();
        }
    }


    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationException {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        } finally {
            this.closeDbConnecton();
        }
    }


    private void closeDbConnecton() throws ApplicationException {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        }
    }
}
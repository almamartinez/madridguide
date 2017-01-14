package io.keepcoding.madridguide;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import io.keepcoding.madridguide.manager.db.ActivityDAO;
import io.keepcoding.madridguide.manager.db.BaseDAO;
import io.keepcoding.madridguide.model.Activity;

public class ActivityDAOTests extends AndroidTestCase {
    public static final String ACTIVITY_TESTING_NAME = "Activity testing name";
    public static final String ADDRESS_TESTING = "Activity Address testing";

    public void testCanInsertANewActivity() {
        final ActivityDAO sut = new ActivityDAO(getContext());

        final int count = getCount(sut);

        final long id = insertTestShop(sut);

        assertTrue(id > 0);
        assertTrue(count + 1 == sut.queryCursor().getCount());
    }

    public void testCanDeleteAnActivity() {
        final ActivityDAO sut = new ActivityDAO(getContext());

        final long id = insertTestShop(sut);

        final int count = getCount(sut);

        assertEquals(1, sut.delete(id));

        assertTrue(count - 1 == sut.queryCursor().getCount());
    }

    public void testDeleteAll() {
        final ActivityDAO sut = new ActivityDAO(getContext());

        sut.deleteAll();

        final int count = getCount(sut);
        assertEquals(0, count);
    }

    public void testQueryOneShop() {
        final ActivityDAO dao = new ActivityDAO(getContext());

        final long id = insertTestShop(dao);

        Activity sut = dao.query(id);
        assertNotNull(sut);
        assertEquals(sut.getName(), ACTIVITY_TESTING_NAME);
    }

    public void testQueryAllShops() {
        final ActivityDAO dao = new ActivityDAO(getContext());

        final long id = insertTestShop(dao);

        List<Activity> activityList = dao.query();
        assertNotNull(activityList);
        assertTrue(activityList.size() > 0);

        for (Activity activity: activityList) {
            assertTrue(activity.getName().length() > 0);
        }
    }

    private int getCount(BaseDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }

    private long insertTestShop(ActivityDAO sut) {
        final Activity activity = new Activity(1, ACTIVITY_TESTING_NAME).setAddress(ADDRESS_TESTING);
        return sut.insert(activity);
    }
}

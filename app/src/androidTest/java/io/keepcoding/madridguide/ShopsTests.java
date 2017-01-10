package io.keepcoding.madridguide;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;


public class ShopsTests extends AndroidTestCase {
    public void testCreatingAShopsWithNullListReturnsNonNullShops() {
        Shops sut = (Shops) Shops.build(null);
        assertNotNull(sut);
        assertNotNull(sut.getAll());
    }

    public void testCreatingAShopsWithAListReturnsNonNullShops() {
        List<Shop> data = getShops();

        Shops sut = (Shops) Shops.build(data);
        assertNotNull(sut);
        assertNotNull(sut.getAll());
        assertEquals(sut.getAll(), data);
        assertEquals(sut.getAll().size(), data.size());
    }

    @NonNull
    private List<Shop> getShops() {
        List<Shop> data = new ArrayList<>();
        data.add(new Shop(1, "1").setAddress("AD 1"));
        data.add(new Shop(2, "2").setAddress("AD 2"));
        return data;
    }

}

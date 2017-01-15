package io.keepcoding.madridguide.interactors;

import android.content.Context;

import java.util.List;

import io.keepcoding.madridguide.manager.db.DBConstants;
import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.util.MainThread;

public class GetAllShopsFromLocalCacheInteractor {
    private String query;

    public GetAllShopsFromLocalCacheInteractor(String query) {
        this.query = query;
    }

    public interface  OnGetAllShopsFromLocalCacheInteractorCompletion {
        public void completion(Shops shops);
    }

    public void execute(final Context context, final OnGetAllShopsFromLocalCacheInteractorCompletion completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);

                List<Shop> shopList = null;
                if (query == null) {
                    shopList = dao.query();
                } else {
                    shopList = dao.query(DBConstants.Shop.KEY_SHOP_NAME + " LIKE ? ", new String[]{ query });
                }
                final Shops shops = Shops.build(shopList);

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(shops);
                    }
                });


            }
        }).start();
    }
}

package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;

public class CacheAllShopsInteractor {
    public interface CacheAllShopsInteractorResponse {
        public void response(boolean success);
    }

    public void execute(final Context context, final @NonNull Shops shops, final CacheAllShopsInteractorResponse response) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (context == null || shops == null) {
                    Log.w("CacheAllShopsInteractor", "Context or Shops null");
                    return;
                }

                ShopDAO dao = new ShopDAO(context);

                boolean success = true;
                for (Shop shop: shops.getAll()) {
                    success = dao.insert(shop) > 0;
                    if (!success) {
                        break;
                    }
                }

                Looper main = Looper.getMainLooper();
                // TODO: put on Main Thread
                if (response != null) {
                    response.response(success);
                }
            }
        }).start();
    }
}

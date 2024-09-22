package nb_wallet_api.entity;

import com.google.gson.Gson;
import talentlms.api.utils.JsonUtils;

public abstract class BaseEntity {
    public String toJson() {
        return new Gson().toJson(this);
    }
}
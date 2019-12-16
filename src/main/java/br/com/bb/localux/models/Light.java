package br.com.bb.localux.models;

import io.vertx.axle.sqlclient.Row;
import io.vertx.axle.sqlclient.RowSet;
import io.vertx.axle.sqlclient.Tuple;
import io.vertx.axle.mysqlclient.MySQLPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class Light {
    public Integer id;
    public String name;
    public Integer status;

    public Light(Integer id, String name, Integer status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Light(String name) {
        this.name = name;
    }



    private static Light from(Row row) {
        return new Light(row.getInteger("id"), row.getString("name"), row.getInteger("status"));
    }

    public static CompletionStage<List<Light>> findAll(MySQLPool client) {
        return client.query("SELECT id, name, status FROM lights ORDER BY name ASC").thenApply(mysqlRowSet -> {
            List<Light> list = new ArrayList<>(mysqlRowSet.size());
            for (Row row : mysqlRowSet) {
                list.add(from(row));
            }
            return list;
        });
    }

    public static CompletionStage<Light> findById(MySQLPool client, Integer id) {
        return client.query("SELECT id, name, status FROM lights WHERE id = " + id)
                .thenApply(RowSet::iterator)
                .thenApply(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    public static void toggleLightStatus(MySQLPool client, Integer id) {
        client.query("UPDATE lights SET status = !status WHERE id = " + id);
    }


}

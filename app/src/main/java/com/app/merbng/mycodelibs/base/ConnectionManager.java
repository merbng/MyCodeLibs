package com.app.merbng.mycodelibs.base;

import android.content.Context;

import com.app.merbng.mycodelibs.utils.AppSystemUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * use:manager the connect: start, cancel and destory;
 * Created by taojunbin on 2016/5/16.
 */
public class ConnectionManager implements IConnectionManager {
    private static ConnectionManager instance;
    private static ExecutorService power;
    private static HashMap<String, List<Connection>> objects;

    private ConnectionManager() {
        power = Executors.newCachedThreadPool();
        objects = new HashMap<>();
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    @Override
    public void connect(Connection connection) {
        if (connection != null) {
            String currentName = AppSystemUtils.getCurrentRunningActivity(connection.getmContext());
            if (objects.containsKey(currentName)) {
                List<Connection> connections = objects.get(currentName);
                connections.add(connection);
            } else {
                List<Connection> connections = new ArrayList<>();
                connections.add(connection);
                objects.put(currentName, connections);
            }
            power.execute(connection.getTask());
        }
    }

    @Override
    public String getResult(Connection connection) {
        if (connection == null) {
            return null;
        }
        String currentName = AppSystemUtils.getCurrentRunningActivity(connection.getmContext());
        if (objects.containsKey(currentName)) {
            List<Connection> connections = objects.get(currentName);
            connections.add(connection);
        } else {
            List<Connection> connections = new ArrayList<>();
            connections.add(connection);
            objects.put(currentName, connections);
        }
        return connection.work();
    }

    @Override
    public void cancelAll() {
        power.execute(new Runnable() {
            @Override
            public void run() {
                Iterator<Map.Entry<String, List<Connection>>> iterator = objects.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = iterator.next();
                    List<Connection> connections = (List<Connection>) entry.getValue();
                    for (Connection c : connections) {
                        c.cancel();
                    }
                    connections.clear();
                }
                objects.clear();
            }
        });
    }

    @Override
    public void cancelCurrent(final Context mContext) {
        power.execute(new Runnable() {
            @Override
            public void run() {
                String currentName = AppSystemUtils.getCurrentRunningActivity(mContext);
                if (objects.containsKey(currentName)) {
                    List<Connection> connections = objects.get(currentName);
                    for (Connection c : connections) {
                        c.cancel();
                    }
                    connections.clear();
                    objects.remove(currentName);
                }
            }
        });
    }

    @Override
    public void destory() {
        try {
            cancelAll();
            power.shutdownNow();
            power = null;
            objects = null;
            instance = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

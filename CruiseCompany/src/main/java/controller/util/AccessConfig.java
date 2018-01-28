package controller.util;

import model.entity.Role;

import java.util.*;
import java.util.regex.Pattern;

public class AccessConfig {
    private HashMap<Pattern,  Set<Role>> access;
    public HashMap<Pattern,  Set<Role>> getAccess() {
        return access;
    }
    private static AccessConfig instances;

    private AccessConfig() {
    }

    {
        Set<Role> all = new HashSet<>(
                Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN)
        );
        Set<Role> authorized = new HashSet<>(
                Arrays.asList(Role.USER, Role.ADMIN)
        );
        Set<Role> unauthorized = new HashSet<>(
                Collections.singletonList(Role.GUEST)
        );
        Set<Role> admin = new HashSet<>(
                Collections.singletonList(Role.ADMIN)
        );

        access = new HashMap<>();
        access.put(Pattern.compile("/profile"), authorized);
        access.put(Pattern.compile("/login"), unauthorized);
        access.put(Pattern.compile("/signin"), unauthorized);
        access.put(Pattern.compile("/signup"), unauthorized);
        access.put(Pattern.compile("/main"), all);
        access.put(Pattern.compile("/"), all);
        access.put(Pattern.compile("/registration"), unauthorized);
        access.put(Pattern.compile("/logout"), authorized);
        access.put(Pattern.compile("/tour/\\d{1,5}"), all);
        access.put(Pattern.compile("/ticket/\\d{1,5}"), authorized);
        access.put(Pattern.compile("/ticket/confirm"), authorized);
        access.put(Pattern.compile("/ticket/excursion"), authorized);
        access.put(Pattern.compile("/ua"), all);
        access.put(Pattern.compile("/eng"), all);
        access.put(Pattern.compile("/ticket/buy"), authorized);
        access.put(Pattern.compile("/profile"), authorized);
        access.put(Pattern.compile("/addCruise"), admin);
        access.put(Pattern.compile("/addTickets"), admin);
        access.put(Pattern.compile("/cruiseEditor"), admin);
        access.put(Pattern.compile("/search/\\d{1,3}"), all);
        access.put(Pattern.compile("/search"), all);
        access.put(Pattern.compile("/main/\\d{1,3}"), all);
    }


    public static AccessConfig getAccessConfig() {
        if (instances == null) {
            synchronized (AccessConfig.class) {
                if (instances == null) {
                    instances = new AccessConfig();
                }
            }
        }
        return instances;
    }
}

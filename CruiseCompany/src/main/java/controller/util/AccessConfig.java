package controller.util;

import model.util.Role;

import java.util.*;
import java.util.regex.Pattern;

public class AccessConfig {
    private HashMap<Pattern, LinkedList<Role>> access;
    private static AccessConfig instances;
    public HashMap<Pattern, LinkedList<Role>> getAccess() {
        return access;
    }

    private void init() {
        LinkedList<Role> all = new LinkedList<>(
                Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN)
        );
        LinkedList<Role> authorized = new LinkedList<>(
                Arrays.asList(Role.USER, Role.ADMIN)
        );
        LinkedList<Role> unauthorized = new LinkedList<>(
                Collections.singletonList(Role.GUEST)
        );
        LinkedList<Role> admin = new LinkedList<>(
                Collections.singletonList(Role.ADMIN)
        );
        access = new HashMap<>();
        access.put(Pattern.compile("/profile"), authorized);
        access.put(Pattern.compile("/login"), unauthorized);
        access.put(Pattern.compile("/main"), all);
        access.put(Pattern.compile("/"), all);
        access.put(Pattern.compile("/registration"), unauthorized);
        access.put(Pattern.compile("/logout"), authorized);
        access.put(Pattern.compile("/tour/\\d{1,5}"), all);
        access.put(Pattern.compile("/ticket"), authorized);
    }

    private AccessConfig() {
        init();
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

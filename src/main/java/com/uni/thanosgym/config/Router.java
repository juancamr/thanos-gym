package com.uni.thanosgym.config;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.awt.BorderLayout;

public class Router {

    private Map<String, JPanel> routes = new HashMap<>();
    private Map<String, Class<? extends JPanel>> routesClasses = new HashMap<>();
    private JFrame window = new JFrame();
    private static Router router;

    public static Router getInstance() {
        if (router == null)
            router = new Router();
        return router;
    }

    public void init(int dimensions[], String packageRoute) {
        int width = dimensions[0];
        int height = dimensions[1];
        System.out.println("Inicializando la ventana principal...");
        window = new JFrame();
        window.setResizable(false);
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setVisible(true);

        // init route classes
        System.out.println("Inicializando rutas...");
        Reflections reflections = new Reflections(packageRoute, new SubTypesScanner(false));
        Set<Class<? extends JPanel>> subTypes = reflections.getSubTypesOf(JPanel.class);
        for (Class<? extends JPanel> clazz : subTypes) {
            String path = clazz.getAnnotation(Route.class).value();
            routesClasses.put(path, clazz);
        }
    }

    public void go(String route) {
        JPanel panel = findPanel(route);
        window.getContentPane().removeAll();
        window.getContentPane().add(panel, BorderLayout.CENTER);
        window.revalidate();
        window.repaint();
    }

    private JPanel findPanel(String route) {
        JPanel panel = routes.get(route);
        if (panel == null) {
            try {
                panel = routesClasses.get(route).getConstructor().newInstance();
                routes.put(route, panel);
                return panel;
            } catch (Exception error) {
                System.out.println(error);
                return null;
            }
        }
        return panel;
    }
}

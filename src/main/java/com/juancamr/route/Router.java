package com.juancamr.route;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.awt.BorderLayout;

public class Router {

    private Map<String, Map<String, JPanel>> routes = new HashMap<>();

    private Map<String, Class<? extends LayoutPanel>> layoutsClasses = new HashMap<>();

    private Map<String, ArrayList<Class<? extends JPanel>>> anidados = new HashMap<>();

    private JFrame window;
    private static Router router;

    private LayoutPanel currentLayout;

    public static Router getInstance() {
        if (router == null)
            router = new Router();
        return router;
    }

    public void init(int dimensions[], String packageRoute, String packageLayout) {
        int width = dimensions[0];
        int height = dimensions[1];
        System.out.println("Inicializando la ventana principal...");
        window = new JFrame();
        window.setResizable(false);
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        // init layout classes
        System.out.println("Inicializando layouts...");
        Reflections reflections2 = new Reflections(packageLayout, new SubTypesScanner(false));
        Set<Class<? extends LayoutPanel>> subTypes2 = reflections2.getSubTypesOf(LayoutPanel.class);
        if (subTypes2.isEmpty()) {
            System.out.println("No se encontraron layouts");
        } else {
            for (Class<? extends LayoutPanel> clazz : subTypes2) {
                String path = clazz.getAnnotation(Layout.class).value();
                layoutsClasses.put(path, clazz);

            }
        }

        // init route classes
        System.out.println("Inicializando rutas...");
        Reflections reflections = new Reflections(packageRoute, new SubTypesScanner(false));
        Set<Class<? extends JPanel>> subTypes = reflections.getSubTypesOf(JPanel.class);
        if (subTypes.isEmpty()) {
            System.out.println("No se encontraron rutas");
            return;
        }
        for (Class<? extends JPanel> clazz : subTypes) {
            String path = clazz.getAnnotation(Route.class).value();
            String layout = null;
            if (path.contains(":")) {
                layout = path.split(":")[0];
                path = path.split(":")[1];
            }
            ArrayList<Class<? extends JPanel>> components = new ArrayList<>();
            components.add(clazz);
            components.add(layoutsClasses.get(layout));
            anidados.put(path, components);
        }

    }

    public void go(String route) {
        JPanel[] components = findPanelAndLayout(route);
        JPanel panel = components[0];
        LayoutPanel layout = (LayoutPanel) components[1];

        if (layout == null) {
            window.getContentPane().removeAll();
            window.getContentPane().add(panel, BorderLayout.CENTER);
            window.revalidate();
            window.repaint();
            window.setVisible(true);
            return;
        }

        layout.getContent().removeAll();
        layout.getContent().add(panel, BorderLayout.CENTER);
        layout.revalidate();
        layout.repaint();

        if (currentLayout != layout) {
            window.getContentPane().removeAll();
            window.getContentPane().add(layout, BorderLayout.CENTER);
            window.revalidate();
            window.repaint();
        }
        if (!window.isVisible()) {
            window.setVisible(true);
        }
    }

    private JPanel[] findPanelAndLayout(String route) {
        Map<String, JPanel> routeComponents = routes.get(route);
        if (routeComponents == null) {
            try {
                ArrayList<Class<? extends JPanel>> rComponents = anidados.get(route);
                if (rComponents == null) {
                    System.out.println("La ruta \"" + route + "\" no existe");
                    System.exit(0);
                }
                JPanel panel = rComponents.get(0).getConstructor().newInstance();
                LayoutPanel panelLayout = null;
                if (rComponents.get(1) != null) {
                    panelLayout = (LayoutPanel) rComponents.get(1).getConstructor().newInstance();
                }

                Map<String, JPanel> elements = new HashMap<>();
                elements.put("panel", panel);
                elements.put("layout", panelLayout);

                routes.put(route, elements);
                return new JPanel[] { panel, panelLayout };
            } catch (Exception error) {
                System.out.println(error);
                return null;
            }
        }
        JPanel panel = routeComponents.get("panel");
        JPanel panelLayout = routeComponents.get("layout");
        return new JPanel[] { panel, panelLayout };
    }

    public JFrame getMainWindow() {
        return window;
    }
}

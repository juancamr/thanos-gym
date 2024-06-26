package com.uni.thanosgym.components;

import java.awt.Color;
import java.io.File;

import javax.swing.JPanel;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;

public class ChooserComponent extends JPanel {

    private File selected;
    public static int height = 30;

    public ChooserComponent(Builder builder) {
        setLayout(null);
        setBackground(Color.WHITE);
        setSize(builder.width, 30);
        setLocation(builder.x, builder.y);

        Typography label = new Typography.Builder()
                .text("Sin seleccionar")
                .type(Typography.Type.SMALL)
                .position(builder.width / 2, 0)
                .width(builder.width / 2)
                .build();

        ButtonComponent button = new ButtonComponent.Builder()
                .text("Seleccionar")
                .position(0, 0)
                .width(builder.width / 2 - 10)
                .type(ButtonComponent.Type.SMALL)
                .onClick(() -> {
                    Response<File> response = FrameUtils.chooseImage(this);
                    if (response.isSuccess()) {
                        selected = response.getData();
                        label.setText(selected.getName());
                    } else {
                        Messages.show("Selecciona una imagen");
                    }
                })
                .build();
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);

        add(button);
        add(label);
    }

    public File getSelected() {
        return selected;
    }

    public static class Builder {
        private int x = 0;
        private int y = 0;
        private int width = 0;

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public ChooserComponent build() {
            return new ChooserComponent(this);
        }
    }
}

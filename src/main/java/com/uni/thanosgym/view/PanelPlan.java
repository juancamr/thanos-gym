package com.uni.thanosgym.view;

import java.awt.Component;

import com.uni.thanosgym.components.Typography;

public class PanelPlan extends BasePanelForMainWindow {

    @Override
	protected Component[] build() {
        Typography title = new Typography.Builder()
                .text("Planes")
                .type(Typography.Type.HEADING)
                .position(0, 0)
                .width(contentWidth)
                .build();


        return new Component[] {
            title
        };
    }
}

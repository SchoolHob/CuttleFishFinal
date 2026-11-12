package Styles;

import input.MouseHandler;

import java.awt.*;
import java.util.logging.Logger;

public class StyleBuilder {
    private static final Logger logger = Logger.getLogger(StyleBuilder.class.getName());

    private Style style;
    public StyleBuilder standard(){
        style = Style.DEFAULT;
        return this;
    }
    public StyleBuilder fill(Color fill){
        style.setFill(fill);
        return this;
    }
    public StyleBuilder outline(Color outline){
        style.setOutline(outline);
        return this;
    }

    public StyleBuilder grid(Color fill, Color outline){
        style = new GridStyle(fill, outline);
        return this;
    }
    public StyleBuilder grid(Color fill, Color secondaryFill, Color outline){
        style = new GridStyle(fill, secondaryFill, outline);
        return this;
    }
    public StyleBuilder grid() {
        style = new GridStyle(Style.DEFAULT.fill, Style.DEFAULT.outline);
        return this;
    }

    public StyleBuilder secondaryFill(Color secondaryFill){
        if (style instanceof GridStyle){
            ((GridStyle)style).setSecondaryFill(secondaryFill);
        } else {
            logger.warning("Style was not set to GridStyle but secondaryFill(Color) was called.");
        }
        return this;
    }


    public Style build() {
        return style;
    }

    public GridStyle buildGrid() {
        if (style instanceof GridStyle){
            return (GridStyle) build();
        } else {
            logger.warning("Style was not set to GridStyle but buildGrid() was called.");
            return new GridStyle(Style.DEFAULT.fill, Style.DEFAULT.outline);
        }
    }

}

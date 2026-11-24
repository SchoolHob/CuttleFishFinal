package Styles;

import java.awt.*;

public class GridStyle extends Style {
    protected Color secondaryFill;
    protected boolean cellsHaveOutlines;

    protected GridStyle(Color fill, Color secondaryFill, Color outline, boolean cellsHaveOutlines) {
        super(fill, outline);
        this.secondaryFill = secondaryFill;
        this.cellsHaveOutlines = cellsHaveOutlines;
    }

    public boolean cellsHaveOutlines() {
        return cellsHaveOutlines;
    }

    public Color getSecondaryFill() { return secondaryFill; }

    public static GridStyleBuilder builder() {
        return new GridStyleBuilder();
    }

    public static class GridStyleBuilder extends StyleBuilder {

        private Color secondaryFill;
        private boolean cellsHaveOutlines;
        protected GridStyleBuilder() {
            super();
            secondaryFill = this.fill;
            cellsHaveOutlines = true;
        }

        public GridStyleBuilder secondaryFill(Color fill) {
            this.secondaryFill = fill;
            cellsHaveOutlines = false;
            return this;
        }

        public GridStyleBuilder cellsHaveOutlines(boolean cellsHaveOutlines) {
            this.cellsHaveOutlines = cellsHaveOutlines;
            return this;
        }

        @Override
        public GridStyleBuilder fill(Color fill) {
            super.fill(fill);
            return this;
        }

        @Override
        public GridStyleBuilder outline(Color outline) {
            super.outline(outline);
            return this;
        }

        @Override
        public GridStyle build() {
            return new GridStyle(fill, secondaryFill, outline, cellsHaveOutlines);
        }


    }

}

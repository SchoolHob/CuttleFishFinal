package Styles;

import java.awt.*;

public class GridStyle extends Style {
    private Color secondaryFill;
    private boolean cellsHaveOutlines;

    public GridStyle(Color fill, Color outline) {
        super(fill, outline);
        cellsHaveOutlines = true;
    }
    public GridStyle(Color fill, Color secondaryFill, Color outline) {
        super(fill, outline);
        cellsHaveOutlines = false;
    }
    public void setCellsHaveOutlines(boolean cellsHaveOutlines) {
        this.cellsHaveOutlines = cellsHaveOutlines;
    }
    public boolean cellsHaveOutlines() {
        return cellsHaveOutlines;
    }
    public void setSecondaryFill(Color fill) {
        secondaryFill = fill;
    }
    public Color getSecondaryFill() { return secondaryFill; }

}

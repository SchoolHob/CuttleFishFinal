package primitive;


import Styles.GridStyle;
import vector.Vector2;
import vector.Vector3;

import java.awt.*;

public class Grid extends Rectangle {
    private static final GridStyle DEFAULT_STYLE = new GridStyle(Color.BLACK, Color.WHITE);

    private int rows, cols;
    private int cellsize;
    private GridStyle gridStyle;

    public Grid(int rows, int collumns, int cellSize, Vector3 position) {
        this.cellsize = cellSize;
        this.rows = rows;
        this.cols = collumns;

        Vector2 size = new Vector2(collumns*cellSize, rows*cellSize);
        super(position, size);

        this.gridStyle = DEFAULT_STYLE;
    }

    public void setStyle(GridStyle gridStyle) {
        gridStyle = gridStyle;
    }


    @Override
    public void draw(Graphics g) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g.setColor((r + c) % 2 == 0 ? gridStyle.getFill() : gridStyle.getSecondaryFill());
                if (gridStyle.cellsHaveOutlines()) {
                    g.fillRect(r * cellsize + getPosition().intx(), c * cellsize + getPosition().inty(), cellsize, cellsize);
                    g.setColor(gridStyle.getOutline());
                    g.drawRect(r * cellsize + getPosition().intx(), c * cellsize + getPosition().inty(), cellsize, cellsize);
                } else {
                    g.fillRect(r * cellsize + getPosition().intx(), c * cellsize + getPosition().inty(), cellsize, cellsize);
                }
            }
        }
    }
}

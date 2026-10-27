package primitive;


import vector.Vector2;
import vector.Vector3;

import java.awt.*;

public class Grid extends Rectangle {
    private int rows, cols;
    private int cellsize;

    public Grid(int rows, int collumns, int cellSize, Vector3 position) {
        this.cellsize = cellSize;
        this.rows = rows;
        this.cols = collumns;

        Vector2 size = new Vector2(collumns*cellSize, rows*cellSize);
        super(position, size);
    }

    @Override
    public void draw(Graphics g) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g.drawRect(r*cellsize+getPosition().intx(), c*cellsize+getPosition().inty(), cellsize, cellsize);
            }
        }
    }
}

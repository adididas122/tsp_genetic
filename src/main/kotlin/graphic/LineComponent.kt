package graphic

import Gene

import javax.swing.*
import java.awt.*
import java.awt.geom.Line2D
import java.util.ArrayList

internal class LineComponent(width: Int, height: Int) : JComponent() {
    private val lines: MutableList<Line2D.Double>

    init {
        preferredSize = Dimension(width, height)
        lines = mutableListOf()
    }

    fun addLine(c1: Gene, c2: Gene) {
        val line = Line2D.Double(c1.x.toDouble(), c1.y.toDouble(), c2.x.toDouble(), c2.y.toDouble())
        lines.add(line)
    }

    public override fun paintComponent(g: Graphics) {
        g.color = Color.white
        g.fillRect(0, 0, width, height)
        g.color = Color.black
        for (line in lines) {
            g.drawLine(
                line.getX1().toInt(),
                line.getY1().toInt(),
                line.getX2().toInt(),
                line.getY2().toInt()
            )
        }
    }
}
package graphic

import Gene
import javax.swing.JOptionPane

object DrawLines {
    fun draw(genes: List<Gene>) {
        val lineComponent = LineComponent(400, 400)
        for (a in 0 until genes.size - 1) {
            lineComponent.addLine(
                genes[a],
                genes[a + 1]
            )
        }
        lineComponent.addLine(
            genes[0],
            genes[genes.size - 1]
        )
        JOptionPane.showMessageDialog(null, lineComponent)
    }
}

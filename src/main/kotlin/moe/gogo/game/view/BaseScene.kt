package moe.gogo.game.view

import moe.gogo.game.input.MouseEvent
import moe.gogo.game.input.MouseEventHandler
import moe.gogo.game.utils.EMPTY_IMAGE
import moe.gogo.game.utils.buildImage
import java.awt.Graphics2D
import java.awt.image.BufferedImage

/**
 * 默认实现的[Scene]，创建[Layer]时默认创建[UILayer]
 */
open class BaseScene : Scene() {

    val layerList: MutableList<Layer> = mutableListOf()

    override val layers
        get() = layerList

    override val size: Int
        get() = layerList.size

    override fun <T : Layer> createLayer(builder: (Scene) -> T, index: Int): T = builder(this).also {
        layerList.add(index, it)
    }

    override fun render(camera: Camera) {
        val image: BufferedImage = buildImage(camera.screenRange)
        val graphics: Graphics2D = image.createGraphics()
        drawImage(camera, graphics)
        this.image = image
    }

    private fun drawImage(camera: Camera, graphics: Graphics2D) {
        layerList.forEach {
            it.render(camera)
            graphics.drawImage(it.image, 0, 0, null)
        }
    }

    override var image: BufferedImage = EMPTY_IMAGE

    // 层的顺序为侧从顶开层始
    override val mouseEventHandler: MouseEventHandler = SceneMouseEventHandler({ this.layers.reversed() })

    private class SceneMouseEventHandler(val layers: () -> List<Layer>) : MouseEventHandler {
        override fun handle(event: MouseEvent) {
            if (event.consumed) {
                return
            }
            /**
             * 从顶层开始遍历各层，直至鼠标事件被处理
             */
            layers().forEach {
                it.mouseEventHandler(event)
                if (event.consumed) {
                    return
                }
            }
        }
    }
}
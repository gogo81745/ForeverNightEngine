package moe.gogo.game.component

import moe.gogo.game.shape.Rect
import moe.gogo.game.shape.Shape
import moe.gogo.game.utils.Point

/**
 * 具有一定形状的组件，用于产生碰撞、点击等当面
 *
 * 注意，获取的shape对象的position 应该保持与 container.position 相同，否则可能会造成位置错误。
 * 一种解决思路是，在getShape中使用 `shape.shiftTo(container.position)` 方法修正位置
 * @see Shape
 */
abstract class ShapeComponent : Component() {

    /**
     * 对象的形状
     */
    abstract val shape: Shape

    /**
     * 某个点是否包含在对象的形状中
     * @param point 判断是否包含的点
     * @return 为true则代表该点在形状中
     */
    open operator fun contains(point: Point): Boolean = shape.contains(point)

    /**
     * 对象是否与另一个形状相接触
     * @param other 需要判断接触另一个形状
     * @return 为true则代表两个形状接触
     * @see [Shape.contact]
     */
    open fun contact(other: Shape): Boolean = shape.contact(other)

    /**
     * 包围此形状的矩形
     * @return 包围此形状的矩形
     * @see [Shape.boundingRect]
     */
    open fun boundingRect(): Rect = shape.boundingRect()

}
package org.sisioh.dddbase.core.lifecycle.forwarding.async

import org.sisioh.dddbase.core.lifecycle.EntitiesChunk
import org.sisioh.dddbase.core.lifecycle.async.{AsyncEntityReadableByPredicate, AsyncEntityReader}
import org.sisioh.dddbase.core.model.{Entity, Identity}
import scala.concurrent.Future

/**
 * [[org.sisioh.dddbase.core.lifecycle.async.AsyncEntityReadableByPredicate]]のデコレータ。
 *
 * @tparam ID 識別子の型
 * @tparam E エンティティの型
 */
trait ForwardingAsyncEntityReaderByPredicate[ID <: Identity[_], E <: Entity[ID]]
  extends AsyncEntityReadableByPredicate[ID, E] {
  this: AsyncEntityReader[ID, E] =>

  /**
   * デリゲート。
   */
  protected val delegateAsyncEntityReaderByPredicate: AsyncEntityReadableByPredicate[ID, E]

  def filterByPredicate
  (predicate: (E) => Boolean,
   index: Option[Int], maxEntities: Option[Int]): Future[EntitiesChunk[ID, E]] =
    delegateAsyncEntityReaderByPredicate.filterByPredicate(predicate, index, maxEntities)

}
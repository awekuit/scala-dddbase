/*
 * Copyright 2011-2013 Sisioh Project and others. (http://www.sisioh.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.sisioh.dddbase.lifecycle.forwarding.sync

import org.sisioh.dddbase.core.lifecycle.EntitiesChunk
import org.sisioh.dddbase.core.lifecycle.sync.{SyncEntityReadableAsChunk, SyncEntityReader}
import org.sisioh.dddbase.core.model.{Identifier, Entity}
import scala.util.Try

/**
 * `SyncEntityReadableAsChunk`のデコレータ。
 *
 * @tparam ID 識別子の型
 * @tparam E エンティティの型
 */
trait ForwardingSyncEntityReadableAsChunk[ID <: Identifier[_], E <: Entity[ID]]
  extends SyncEntityReadableAsChunk[ID, E] {
  this: SyncEntityReader[ID, E] =>

  type Delegate <: SyncEntityReadableAsChunk[ID, E]

  /**
   * デリゲート。
   */
  protected val delegate: Delegate

  def resolveAsChunk(index: Int, maxEntities: Int)
                    (implicit ctx: Ctx): Try[EntitiesChunk[ID, E]] =
    delegate.resolveAsChunk(index, maxEntities)

}



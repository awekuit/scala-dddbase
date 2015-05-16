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
package org.sisioh.dddbase.lifecycle.forwarding.sync.wrapped

import org.sisioh.dddbase.core.lifecycle.async.AsyncEntityIOContext
import org.sisioh.dddbase.core.lifecycle.sync.SyncEntityIOContext

/**
 * `org.sisioh.dddbase.core.lifecycle.async.AsyncEntityIOContext`を
 * `org.sisioh.dddbase.core.lifecycle.sync.SyncEntityIOContext`として
 * ラップするトレイト。
 */
trait SyncWrappedAsyncEntityIOContext extends SyncEntityIOContext {

  /**
   * `org.sisioh.dddbase.core.lifecycle.async.AsyncEntityIOContext`
   */
  val asyncEntityIOContext: AsyncEntityIOContext

}

/**
 * コンパニオンオブジェクト。
 */
object SyncWrappedAsyncEntityIOContext {

  /**
   * ファクトリメソッド。
   *
   * @param asyncEntityIOContext `org.sisioh.dddbase.core.lifecycle.async.AsyncEntityIOContext`
   * @return `org.sisioh.dddbase.lifecycle.forwarding.sync.wrapped.SyncWrappedAsyncEntityIOContext`
   */
  def apply(asyncEntityIOContext: AsyncEntityIOContext): SyncWrappedAsyncEntityIOContext =
    new SyncWrappedEntityIOContextImpl(asyncEntityIOContext)

  /**
   * エクストラクタメソッド。
   *
   * @param syncWrappedEntityIOContext `org.sisioh.dddbase.lifecycle.forwarding.sync.wrapped.SyncWrappedAsyncEntityIOContext`
   * @return 構成要素
   */
  def unapply(syncWrappedEntityIOContext: SyncWrappedAsyncEntityIOContext): Option[(AsyncEntityIOContext)] =
    Some(syncWrappedEntityIOContext.asyncEntityIOContext)

}

private[wrapped]
case class SyncWrappedEntityIOContextImpl
(asyncEntityIOContext: AsyncEntityIOContext)
  extends SyncWrappedAsyncEntityIOContext


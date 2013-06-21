/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
 * Copyright 2011 Sisioh Project and others. (http://www.sisioh.org/)
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
package org.sisioh.dddbase.core

import scala.concurrent._

/**
 * オンメモリで動作する[[org.sisioh.dddbase.core.AsyncRepository]]の実装。
 *
 * @tparam ID 識別子の型
 * @tparam T エンティティの型
 */
trait AsyncOnMemoryRepository
[AR <: AsyncOnMemoryRepository[AR, SR, ID, T],
SR <: OnMemoryRepository[SR, ID, T],
ID <: Identity[_],
T <: Entity[ID] with EntityCloneable[ID, T]]
  extends AsyncRepository[AR, ID, T] with AsyncEntityReaderByOption[ID, T] {

  protected val core: SR

  protected def createInstance(state: SR): AR

  def resolve(identifier: ID)(implicit executor: ExecutionContext) = future {
    core.resolve(identifier).get
  }

  def resolveOption(identifier: ID)(implicit executor: ExecutionContext) = future {
    core.resolveOption(identifier).get
  }

  def contains(identifier: ID)(implicit executor: ExecutionContext) = future {
    core.contains(identifier).get
  }

  def store(entity: T)(implicit executor: ExecutionContext): Future[AR] = future {
    createInstance(core.store(entity).get)
  }

  def delete(identity: ID)(implicit executor: ExecutionContext): Future[AR] = future {
    createInstance(core.delete(identity).get)
  }

}

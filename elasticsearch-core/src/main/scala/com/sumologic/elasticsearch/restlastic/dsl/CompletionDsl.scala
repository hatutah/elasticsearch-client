/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.sumologic.elasticsearch.restlastic.dsl

trait CompletionDsl extends DslCommons with QueryDsl {

  case class Suggest(text: String, completion: Completion) extends Query with RootObject {
    val _suggest = "suggest"
    val _text = "text"
    val _completion = "completion"

    override def toJson: Map[String, Any] = {
      Map(_suggest -> Map(
        _text -> text,
        _completion -> completion.toJson
      ))
    }
  }

  case class Completion(field: String, size: Int, context: Map[String, String]) extends Query {
    val _field = "field"
    val _context = "context"
    val _size = "size"

    def withAdditionalContext(newContext: (String, String)*) = {
      this.copy(context = context ++ newContext)
    }

    override def toJson: Map[String, Any] = Map(
      _field -> field,
      _context -> context,
      _size -> size
    )
  }
}



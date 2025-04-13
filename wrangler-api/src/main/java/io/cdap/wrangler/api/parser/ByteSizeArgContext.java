/*
 * Copyright © 2017-2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under
 * the License.
 */

 package io.cdap.wrangler.api.parser;

 /**
  * Represents the context for a ByteSize argument.
  * This class holds the value of a ByteSize argument and provides methods to retrieve it.
  */
 public class ByteSizeArgContext {
     private final String value;
 
     /**
      * Constructs a ByteSizeArgContext with the specified value.
      *
      * @param value The value of the ByteSize argument.
      */
     public ByteSizeArgContext(String value) {
         this.value = value;
     }
 
     /**
      * Retrieves the text representation of the ByteSize value.
      *
      * @return The ByteSize value as a string.
      */
     public String getText() {
         return value;
     }
 
     /**
      * Provides a string representation of the ByteSizeArgContext object.
      *
      * @return A string containing the ByteSize argument value.
      */
     @Override
     public String toString() {
         return "ByteSizeArgContext{" + "value='" + value + '\'' + '}';
     }
 }
 

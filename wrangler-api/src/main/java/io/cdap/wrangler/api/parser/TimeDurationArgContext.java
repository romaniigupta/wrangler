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
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.



/**
 * Context class for TimeDuration arguments.
 */

 package io.cdap.wrangler.api.parser;
 

 /**
  * Represents the context for a TimeDuration argument.
  * This class holds the value of a TimeDuration argument and provides methods to retrieve it.
  */
 public class TimeDurationArgContext {
     private final String value;
 
     /**
      * Constructs a TimeDurationArgContext with the specified value.
      *
      * @param value The value of the TimeDuration argument.
      */
     public TimeDurationArgContext(String value) {
         this.value = value;
     }
 
     /**
      * Retrieves the text representation of the TimeDuration value.
      *
      * @return The TimeDuration value as a string.
      */
     public String getText() {
         return value;
     }
 }
  

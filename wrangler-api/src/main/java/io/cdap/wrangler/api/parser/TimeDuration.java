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
 */

 package io.cdap.wrangler.api.parser;

 import com.google.gson.JsonElement;
 import com.google.gson.JsonPrimitive;
 
 /**
  * Represents a token for parsing time duration values (e.g., "150ms", "2min").
  */
 public class TimeDuration implements Token {
     /**
      * The time duration in canonical form (milliseconds).
      */
     private final long milliseconds;
 
     /**
      * The original string representation of the time duration.
      */
     private final String originalValue;
 
     /**
      * Constructs a TimeDuration token from the given string value.
      * @param value The string representation of the time duration (e.g., "150ms").
      * @throws IllegalArgumentException if the value has an invalid unit.
      */
     public TimeDuration(String value) {
         this.originalValue = value;
         this.milliseconds = parseMilliseconds(value);
     }
 
     /**
      * Parses the time duration value and converts it to milliseconds.
      * @param value The string value to parse.
      * @return The time duration in milliseconds.
      * @throws IllegalArgumentException if the unit is invalid.
      */
     private long parseMilliseconds(String value) {
         String unit = value.replaceAll("\\d", "").trim().toLowerCase();
         long number = Long.parseLong(value.replaceAll("\\D", "").trim());
         switch (unit) {
             case "ms":
                 return number;
             case "s":
                 return number * 1000;
             case "min":
                 return number * 60 * 1000;
             default:
                 throw new IllegalArgumentException(
                     "Invalid time duration unit: " + unit
                 );
         }
     }
 
     @Override
     public Object value() {
         return milliseconds;
     }
 
     @Override
     public TokenType type() {
         return TokenType.TIME_DURATION;
     }
 
     @Override
     public JsonElement toJson() {
         return new JsonPrimitive(originalValue);
     }
 
     /**
      * Gets the time duration in milliseconds.
      * @return The time duration.
      */
     public long getMilliseconds() {
         return milliseconds;
     }
    
    
 }


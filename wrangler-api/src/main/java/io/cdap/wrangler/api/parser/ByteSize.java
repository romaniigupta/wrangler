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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

 package io.cdap.wrangler.api.parser;

 import com.google.gson.JsonElement;
 import com.google.gson.JsonPrimitive;
 
 /**
  * Represents a token for parsing byte size values (e.g., "10KB", "1MB").
  */
 public final class ByteSize implements Token {
     private static final long KB_TO_BYTES = 1024L;
     private static final long MB_TO_BYTES = KB_TO_BYTES * 1024;
     private static final long GB_TO_BYTES = MB_TO_BYTES * 1024;
 
     private final long bytes;
     private final String originalValue;
 
     /**
      * Constructs a ByteSize token from the given string value.
      *
      * @param value The string representation of the byte size (e.g., "10KB").
      * @throws IllegalArgumentException if the value has an invalid unit.
      */
     public ByteSize(final String value) {
         this.originalValue = value;
         this.bytes = parseBytes(value);
     }
 
     /**
      * Parses the byte size value and converts it to bytes.
      *
      * @param value The string value to parse.
      * @return The byte size in bytes.
      * @throws IllegalArgumentException if the unit is invalid.
      */
     private long parseBytes(final String value) {
         final String unit = value.replaceAll("\\d", "").trim().toUpperCase();
         final long number = Long.parseLong(value.replaceAll("\\D", "").trim());
         switch (unit) {
             case "KB":
                 return number * KB_TO_BYTES;
             case "MB":
                 return number * MB_TO_BYTES;
             case "GB":
                 return number * GB_TO_BYTES;
             default:
                 throw new IllegalArgumentException("Invalid byte size unit: " + unit);
         }
     }
 
     @Override
     public final Object value() {
         return bytes;
     }
 
     @Override
     public final TokenType type() {
         return TokenType.BYTE_SIZE;
     }
 
     @Override
     public final JsonElement toJson() {
         return new JsonPrimitive(originalValue);
     }
 
     /**
      * Gets the byte size in bytes.
      *
      * @return The byte size.
      */
     public final long getBytes() {
         return bytes;
     }
 } 

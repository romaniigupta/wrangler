// // /*
// //  * Copyright © 2017-2019 Cask Data, Inc.
// //  *
// //  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
// //  * use this file except in compliance with the License. You may obtain a copy of
// //  * the License at
// //  *
// //  * http://www.apache.org/licenses/LICENSE-2.0
// //  *
// //  * Unless required by applicable law or agreed to in writing, software
// //  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// //  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// //  * License for the specific language governing permissions and limitations under
// //  * the License.
// //  */
package io.cdap.wrangler.api.parser;


import org.junit.Assert;
import org.junit.Test;

public class ByteSizeTest {

  @Test
  public void testValidByteSizes() {
    ByteSize kb = new ByteSize("1KB");
    Assert.assertEquals(1024L, kb.getBytes());

    ByteSize mb = new ByteSize("2MB");
    Assert.assertEquals(2 * 1024 * 1024L, mb.getBytes());

    ByteSize gb = new ByteSize("3GB");
    Assert.assertEquals(3L * 1024 * 1024 * 1024, gb.getBytes());

    ByteSize mixedCase = new ByteSize("5mb");
    Assert.assertEquals(5L * 1024 * 1024, mixedCase.getBytes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidUnitThrowsException() {
    new ByteSize("10TB");
  }

  @Test(expected = NumberFormatException.class)
  public void testMissingNumberThrowsException() {
    new ByteSize("MB");
  }

  @Test(expected = NumberFormatException.class)
  public void testEmptyInputThrowsException() {
    new ByteSize("");
  }

  @Test
  public void testToJson() {
    ByteSize size = new ByteSize("4GB");
    Assert.assertEquals("4GB", size.toJson().getAsString());
  }

  @Test
  public void testTokenType() {
    ByteSize size = new ByteSize("1KB");
    Assert.assertEquals(TokenType.BYTE_SIZE, size.type());
  }

  @Test
  public void testValueMethod() {
    ByteSize size = new ByteSize("7MB");
    Assert.assertEquals(7L * 1024 * 1024, size.value());
  }
}


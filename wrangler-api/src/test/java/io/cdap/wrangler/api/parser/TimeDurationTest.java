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

public class TimeDurationTest {

  @Test
  public void testValidDurations() {
    TimeDuration ms = new TimeDuration("500ms");
    Assert.assertEquals(500L, ms.getMilliseconds());

    TimeDuration seconds = new TimeDuration("2s");
    Assert.assertEquals(2000L, seconds.getMilliseconds());

    TimeDuration minutes = new TimeDuration("3min");
    Assert.assertEquals(3 * 60 * 1000L, minutes.getMilliseconds());

    TimeDuration mixedCase = new TimeDuration("4Min");
    Assert.assertEquals(4 * 60 * 1000L, mixedCase.getMilliseconds());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidUnitThrowsException() {
    new TimeDuration("10hours");
  }

  @Test(expected = NumberFormatException.class)
  public void testMissingNumberThrowsException() {
    new TimeDuration("ms");
  }

  @Test(expected = NumberFormatException.class)
  public void testEmptyInputThrowsException() {
    new TimeDuration("");
  }

  @Test
  public void testToJson() {
    TimeDuration duration = new TimeDuration("4s");
    Assert.assertEquals("4s", duration.toJson().getAsString());
  }

  @Test
  public void testTokenType() {
    TimeDuration duration = new TimeDuration("1s");
    Assert.assertEquals(TokenType.TIME_DURATION, duration.type());
  }

  @Test
  public void testValueMethod() {
    TimeDuration duration = new TimeDuration("7s");
    Assert.assertEquals(7000L, duration.value());
  }
}

//
//   ~ Copyright © 2017-2019 Cask Data, Inc.
//   ~
//   ~ Licensed under the Apache License, Version 2.0 (the "License"); you may not
//   ~ use this file except in compliance with the License. You may obtain a copy of
//   ~ the License at
//   ~
//   ~ http://www.apache.org/licenses/LICENSE-2.0
//   ~
//   ~ Unless required by applicable law or agreed to in writing, software
//   ~ distributed under the License is distributed on an "AS IS" BASIS,
//   ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
//   ~ License for the specific language governing permissions and limitations under
//   ~ the License.
//

package io.cdap.directives;

import io.cdap.wrangler.api.DirectiveParseException;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for {@link AggregateStatsDirective}.
 */
public class AggregateStatsDirectiveTest {

  @Test
  public void testExecute() throws DirectiveParseException {
    AggregateStatsDirective directive = new AggregateStatsDirective();
    directive.initialize(null); // No args needed for now

    Row row1 = new Row();
    row1.add("data_transfer_size", new ByteSize("1024KB")); // 1 MB
    row1.add("response_time", new TimeDuration("5000ms")); // 5 seconds

    Row row2 = new Row();
    row2.add("data_transfer_size", new ByteSize("2048KB")); // 2 MB
    row2.add("response_time", new TimeDuration("10000ms")); // 10 seconds

    List<Row> rows = List.of(row1, row2);

    List<Row> result = directive.execute(rows, null);

    Row resultRow = result.get(0);
    double totalSizeMB = (double) resultRow.getValue("total_size_mb");
    double totalTimeSec = (double) resultRow.getValue("total_time_sec");

    Assert.assertEquals(3.0, totalSizeMB, 0.001); // 1 + 2 = 3 MB
    Assert.assertEquals(15.0, totalTimeSec, 0.001); // 5 + 10 = 15 sec
  }
}

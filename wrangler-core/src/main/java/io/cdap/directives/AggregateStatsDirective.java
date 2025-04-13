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

import io.cdap.wrangler.api.Arguments;
import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.DirectiveParseException;
import io.cdap.wrangler.api.ExecutorContext;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.parser.UsageDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * A custom directive that aggregates ByteSize and TimeDuration fields across rows
 * and outputs a single summary row with total size in MB and total time in seconds.
 */
public class AggregateStatsDirective implements Directive {
  private String byteSizeColumn;
  private String timeDurationColumn;
  private String targetSizeColumn;
  private String targetTimeColumn;

  @Override
  public void initialize(Arguments arguments) throws DirectiveParseException {
    // Hardcoded column names (can be updated to accept from args)
    this.byteSizeColumn = "data_transfer_size";
    this.timeDurationColumn = "response_time";
    this.targetSizeColumn = "total_size_mb";
    this.targetTimeColumn = "total_time_sec";
  }

  @Override
  public List<Row> execute(List<Row> rows, ExecutorContext context) {
    long totalSize = 0;
    long totalTime = 0;

    for (Row row : rows) {
      Object sizeObj = row.getValue(byteSizeColumn);
      Object timeObj = row.getValue(timeDurationColumn);

      if (sizeObj instanceof ByteSize && timeObj instanceof TimeDuration) {
        long size = ((ByteSize) sizeObj).getBytes();
        long time = ((TimeDuration) timeObj).getMilliseconds();

        totalSize += size;
        totalTime += time;
      } else {
        throw new RuntimeException("Invalid input: ByteSize or TimeDuration expected");
      }
    }

    Row outputRow = new Row();
    outputRow.add(targetSizeColumn, (double) totalSize / (1024 * 1024)); // Convert to MB
    outputRow.add(targetTimeColumn, (double) totalTime / 1000); // Convert to seconds

    List<Row> result = new ArrayList<>();
    result.add(outputRow);
    return result;
  }

  @Override
  public void destroy() {
    // No-op
  }

  @Override
  public UsageDefinition define() {
    return UsageDefinition.builder("aggregate-stats")
        .build();
  }
}

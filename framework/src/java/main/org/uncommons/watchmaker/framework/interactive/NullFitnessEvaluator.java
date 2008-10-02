// ============================================================================
//   Copyright 2006, 2007, 2008 Daniel W. Dyer
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
// ============================================================================
package org.uncommons.watchmaker.framework.interactive;

import java.util.List;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 * Fitness evaluation is not required for interactive selection, so this stub
 * implementation is used to satisfy the framework requirements.
 * @author Daniel Dyer
 */
public class NullFitnessEvaluator implements FitnessEvaluator<Object>
{
    /**
     * Returns a score of zero, regardless of the candidate being evaluated.
     * @param candidate The individual to evaluate.
     * @param population {@inheritDoc}
     * @return Zero.
     */
    public double getFitness(Object candidate,
                             List<? extends Object> population)
    {
        return 0;
    }

    /**
     * Always returns true.  However, the return value of this method is
     * irrelevant since no meaningful fitness scores are produced.
     * @return True.
     */
    public boolean isNatural()
    {
        return true;
    }
}

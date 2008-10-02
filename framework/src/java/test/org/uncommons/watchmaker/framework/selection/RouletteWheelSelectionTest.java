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
package org.uncommons.watchmaker.framework.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.testng.annotations.Test;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

/**
 * Unit test for roulette selection strategy.  We cannot easily test
 * that the correct candidates are returned because of the random aspect
 * of the selection, but we can at least make sure the right number of
 * candidates are selected.
 * @author Daniel Dyer
 */
public class RouletteWheelSelectionTest
{
    private final Random rng = new MersenneTwisterRNG();

    @Test
    public void testNaturalFitnessSelection()
    {
        SelectionStrategy<Object> selector = new RouletteWheelSelection();
        List<EvaluatedCandidate<String>> population = new ArrayList<EvaluatedCandidate<String>>(4);
        EvaluatedCandidate<String> steve = new EvaluatedCandidate<String>("Steve", 10.0);
        EvaluatedCandidate<String> mary = new EvaluatedCandidate<String>("Mary", 9.1);
        EvaluatedCandidate<String> john = new EvaluatedCandidate<String>("John", 8.4);
        EvaluatedCandidate<String> gary = new EvaluatedCandidate<String>("Gary", 6.2);
        population.add(steve);
        population.add(mary);
        population.add(john);
        population.add(gary);
        for (int i = 0; i < 20; i++) // Run several iterations to get different outcomes from the "roulette wheel".
        {
            List<String> selection = selector.select(population, true, 2, rng);
            assert selection.size() == 2 : "Selection size is " + selection.size() + ", should be 2.";
        }
    }


    @Test
    public void testNonNaturalFitnessSelection()
    {
        SelectionStrategy<Object> selector = new RouletteWheelSelection();
        List<EvaluatedCandidate<String>> population = new ArrayList<EvaluatedCandidate<String>>(4);
        EvaluatedCandidate<String> gary = new EvaluatedCandidate<String>("Gary", 6.2);
        EvaluatedCandidate<String> john = new EvaluatedCandidate<String>("John", 8.4);
        EvaluatedCandidate<String> mary = new EvaluatedCandidate<String>("Mary", 9.1);
        EvaluatedCandidate<String> steve = new EvaluatedCandidate<String>("Steve", 10.0);
        population.add(gary);
        population.add(john);
        population.add(mary);
        population.add(steve);
        for (int i = 0; i < 20; i++) // Run several iterations to get different outcomes from the "roulette wheel".
        {
            List<String> selection = selector.select(population, false, 2, rng);
            assert selection.size() == 2 : "Selection size is " + selection.size() + ", should be 2.";
        }
    }
}

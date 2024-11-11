import pytest
from permutations import generate_permutations

def test_generate_permutations_unique():
    result = generate_permutations("abc", include_duplicates=True)
    expected = ["abc", "acb", "bac", "bca", "cab", "cba"]
    assert sorted(result) == sorted(expected)

def test_generate_permutations_duplicates():
    result = generate_permutations("aab", include_duplicates=True)
    expected = ["aab", "aba", "aab", "aba", "baa", "baa"]  # duplicates included
    assert sorted(result) == sorted(expected)

def test_generate_permutations_exclude_duplicates():
    result = generate_permutations("aab", include_duplicates=False)
    expected = ["aab", "aba", "baa"]  # duplicates excluded
    assert sorted(result) == sorted(expected)

def test_generate_permutations_single_character():
    result = generate_permutations("a", include_duplicates=True)
    expected = ["a"]
    assert result == expected

def test_generate_permutations_empty_string():
    with pytest.raises(ValueError, match="Input string cannot be empty"):
        generate_permutations("")

def test_generate_permutations_all_identical():
    result = generate_permutations("aaa", include_duplicates=False)
    expected = ["aaa"]
    assert result == expected

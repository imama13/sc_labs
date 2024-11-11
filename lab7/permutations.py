def generate_permutations(string, include_duplicates=True):
    """
    Generates all permutations of the given string using a recursive algorithm.

    Parameters:
    string (str): The input string for which permutations are generated.
    include_duplicates (bool): If False, excludes duplicate permutations when characters are identical.

    Returns:
    list: A list of all permutations of the input string.
    """
    if not string:
        raise ValueError("Input string cannot be empty")

    permutations = []
    _permute(list(string), 0, len(string) - 1, permutations, include_duplicates)
    return permutations

def _permute(chars, left, right, permutations, include_duplicates):
    """
    Helper function that uses recursion to generate permutations.

    Parameters:
    chars (list): List of characters to permute.
    left (int): Starting index for permutation.
    right (int): Ending index for permutation.
    permutations (list): Accumulator list to store generated permutations.
    include_duplicates (bool): If False, avoids adding duplicate permutations.
    """
    if left == right:
        perm = ''.join(chars)
        if include_duplicates or perm not in permutations:
            permutations.append(perm)
    else:
        for i in range(left, right + 1):
            # Swap characters to form new permutation
            chars[left], chars[i] = chars[i], chars[left]
            _permute(chars, left + 1, right, permutations, include_duplicates)
            # Swap back for the next iteration
            chars[left], chars[i] = chars[i], chars[left]

# Optional: call the function and print the results
if __name__ == "__main__":
    try:
        input_string = input("Enter a string to permute: ")
        include_duplicates = input("Include duplicates? (yes/no): ").strip().lower() == 'yes'
        results = generate_permutations(input_string, include_duplicates)
        print("Permutations:", results)
        print("Total permutations:", len(results))
    except ValueError as e:
        print(e)

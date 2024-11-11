import os
import sys

def find_files(directory, filenames, case_sensitive=True):
    """
    Recursively searches for specified files within a directory and its subdirectories.
    
    Parameters:
    directory (str): Path to the directory to search.
    filenames (list): List of filenames to search for.
    case_sensitive (bool): Determines if search is case-sensitive.
    
    Returns:
    dict: Dictionary with filenames as keys and lists of full file paths as values.
    """
    found_files = {filename: [] for filename in filenames}
    
    # Adjust filenames for case insensitivity if required
    search_filenames = filenames if case_sensitive else [filename.lower() for filename in filenames]

    try:
        for root, _, files in os.walk(directory):
            for file in files:
                target_file = file if case_sensitive else file.lower()
                
                if target_file in search_filenames:
                    full_path = os.path.join(root, file)
                    found_files[target_file if case_sensitive else filenames[search_filenames.index(target_file)]].append(full_path)

    except FileNotFoundError:
        print(f"Error: The directory '{directory}' does not exist.")
    except Exception as e:
        print(f"An error occurred: {e}")
    
    return found_files


def display_results(found_files):
    """
    Displays search results for each file, including occurrences and paths.
    """
    for filename, paths in found_files.items():
        if paths:
            print(f"File '{filename}' found {len(paths)} time(s):")
            for path in paths:
                print(f" - {path}")
        else:
            print(f"File '{filename}' not found.")


if __name__ == "__main__":
    # Ensure at least 2 arguments are passed (directory path and at least one filename)
    if len(sys.argv) < 3:
        print("Usage: python search_files.py <directory_path> <file_name_1> [<file_name_2> ...] [-i]")
        sys.exit(1)

    directory_path = sys.argv[1]
    filenames = []
    case_sensitive = True
    
    for arg in sys.argv[2:]:
        if arg == '-i':
            case_sensitive = False  # Toggle case insensitivity if '-i' flag is provided
        else:
            filenames.append(arg)
    
    # Search for files
    found_files = find_files(directory_path, filenames, case_sensitive)
    
    # Display results
    display_results(found_files)

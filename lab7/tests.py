import pytest
import tempfile
import os
from search_files import find_files

@pytest.fixture
def setup_test_directory():
    # Create a temporary directory structure with files
    with tempfile.TemporaryDirectory() as temp_dir:
        # Create files in the main directory
        file1 = os.path.join(temp_dir, "example.txt")
        file2 = os.path.join(temp_dir, "sample.doc")
        
        with open(file1, "w") as f:
            f.write("This is example.txt file")
        
        with open(file2, "w") as f:
            f.write("This is sample.doc file")
        
        # Create a subdirectory and add a duplicate file
        sub_dir = os.path.join(temp_dir, "subdirectory")
        os.mkdir(sub_dir)
        file3 = os.path.join(sub_dir, "example.txt")
        
        with open(file3, "w") as f:
            f.write("This is another example.txt in subdirectory")
        
        yield temp_dir  # Provide the temporary directory path to the test

def test_single_file_case_sensitive(setup_test_directory):
    result = find_files(setup_test_directory, ["example.txt"], case_sensitive=True)
    assert "example.txt" in result
    assert len(result["example.txt"]) == 2  # Expecting 2 occurrences

def test_single_file_case_insensitive(setup_test_directory):
    result = find_files(setup_test_directory, ["EXAMPLE.TXT"], case_sensitive=False)
    assert "EXAMPLE.TXT" in result
    assert len(result["EXAMPLE.TXT"]) == 2  # Expecting 2 occurrences, case-insensitive

def test_multiple_files(setup_test_directory):
    result = find_files(setup_test_directory, ["example.txt", "sample.doc"], case_sensitive=True)
    assert "example.txt" in result
    assert "sample.doc" in result
    assert len(result["example.txt"]) == 2
    assert len(result["sample.doc"]) == 1  # Only one sample.doc file in the main directory

def test_file_not_found(setup_test_directory):
    result = find_files(setup_test_directory, ["nonexistent.txt"], case_sensitive=True)
    assert "nonexistent.txt" in result
    assert len(result["nonexistent.txt"]) == 0  # File should not be found

def test_invalid_directory():
    result = find_files("/invalid_directory_path", ["example.txt"], case_sensitive=True)
    assert result == {"example.txt": []}  # Should return an empty list for invalid path

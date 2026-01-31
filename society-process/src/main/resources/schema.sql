CREATE TABLE society (
    -- Primary Key: Storing UUIDs as BINARY(16) is highly efficient for space and indexing.
    society_id VARCHAR(100) PRIMARY KEY COMMENT 'Unique ID of the society (UUID)',

    -- Society details
    name VARCHAR(200) NOT NULL COMMENT 'The name of the society',

    -- Address details
    country VARCHAR(100) NOT NULL COMMENT 'Country of the society',
    state VARCHAR(100) NOT NULL COMMENT 'State of the society',
    city VARCHAR(100) NOT NULL COMMENT 'City of the society',
    address VARCHAR(500) NOT NULL COMMENT 'Detailed address of the society',
    pincode VARCHAR(10) NOT NULL COMMENT 'Postal code/Pincode',
    landmark VARCHAR(200) COMMENT 'Nearby landmark for easy identification (optional)',

    -- Audit/Metadata
    created_by_user_id VARCHAR(100) NOT NULL COMMENT 'The ID of the user who created this society record (UUID)',
    created_at DATETIME NOT NULL COMMENT 'Timestamp when the record was created',
    updated_at DATETIME COMMENT 'Timestamp when the record was last updated',
    -- Storing status as a string (VARCHAR) is common, corresponding to an enum in the application.
    status VARCHAR(20) NOT NULL COMMENT 'Current status of the society (e.g., ACTIVE, PENDING, INACTIVE)'
);

-- Adding a unique index to ensure no two societies have the same name (or a partial index if names can repeat but combined with another field).
-- If society names can be duplicated across cities/states, this index might need to include city/state.
CREATE UNIQUE INDEX uk_society_name ON society (name);

-- Adding an index on status for efficient filtering
CREATE INDEX idx_society_status ON society (status);

-- Adding an index on created_by_user_id for efficient lookups of societies created by a user
CREATE INDEX idx_society_created_by_user ON society (created_by_user_id);
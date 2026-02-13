-- Enable UUID generation
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- =====================
-- USERS & SCHOOLS
-- =====================

CREATE TABLE schools (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    hashed_password TEXT NOT NULL,
    school_id UUID REFERENCES schools(id),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- =====================
-- MUSIC ENTITIES
-- =====================

CREATE TABLE artists (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    musicbrainz_id UUID UNIQUE NOT NULL
);

CREATE INDEX idx_artists_musicbrainz_id ON artists(musicbrainz_id);

CREATE TABLE albums (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    artist_id UUID NOT NULL REFERENCES artists(id) ON DELETE CASCADE,
    musicbrainz_id UUID UNIQUE NOT NULL,
    release_date DATE
);

CREATE INDEX idx_albums_musicbrainz_id ON albums(musicbrainz_id);
CREATE INDEX idx_albums_artist_id ON albums(artist_id);

CREATE TABLE songs (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    artist_id UUID NOT NULL REFERENCES artists(id) ON DELETE CASCADE,
    album_id UUID REFERENCES albums(id) ON DELETE SET NULL,
    musicbrainz_id UUID UNIQUE NOT NULL,
    duration_ms INT
);

CREATE INDEX idx_songs_musicbrainz_id ON songs(musicbrainz_id);
CREATE INDEX idx_songs_artist_id ON songs(artist_id);
CREATE INDEX idx_songs_album_id ON songs(album_id);

-- =====================
-- USER ↔ MUSIC RELATIONSHIPS
-- =====================

CREATE TABLE user_artists (
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    artist_id UUID REFERENCES artists(id) ON DELETE CASCADE,
    play_count INT DEFAULT 0,
    liked BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (user_id, artist_id)
);

CREATE TABLE user_albums (
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    album_id UUID REFERENCES albums(id) ON DELETE CASCADE,
    play_count INT DEFAULT 0,
    liked BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (user_id, album_id)
);

CREATE TABLE user_songs (
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    song_id UUID REFERENCES songs(id) ON DELETE CASCADE,
    play_count INT DEFAULT 0,
    liked BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (user_id, song_id)
);

-- =====================
-- SOCIAL GRAPH
-- =====================

CREATE TABLE follows (
    follower_id UUID REFERENCES users(id) ON DELETE CASCADE,
    following_id UUID REFERENCES users(id) ON DELETE CASCADE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, following_id),
    CHECK (follower_id <> following_id)
);

CREATE TABLE matches (
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    matched_user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    score FLOAT NOT NULL,
    computed_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, matched_user_id),
    CHECK (user_id <> matched_user_id)
);

-- =====================
-- MESSAGING
-- =====================

CREATE TABLE conversations (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE conversation_members (
    conversation_id UUID REFERENCES conversations(id) ON DELETE CASCADE,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (conversation_id, user_id)
);

CREATE TABLE messages (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    conversation_id UUID REFERENCES conversations(id) ON DELETE CASCADE,
    sender_id UUID REFERENCES users(id) ON DELETE CASCADE,
    content TEXT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- =====================
-- DISCUSSIONS & COMMENTS
-- =====================

CREATE TYPE topic_type_enum AS ENUM ('artist', 'album', 'song');

CREATE TABLE discussion_topics (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    topic_type topic_type_enum NOT NULL,
    topic_ref_id UUID NOT NULL
);

CREATE TABLE comments (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    topic_id UUID REFERENCES discussion_topics(id) ON DELETE CASCADE,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    content TEXT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE comment_likes (
    comment_id UUID REFERENCES comments(id) ON DELETE CASCADE,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (comment_id, user_id)
);


-- -- Enable UUID generation
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- -- =====================
-- -- USERS & SCHOOLS
-- -- =====================

-- CREATE TABLE schools (
--     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     name VARCHAR(255) UNIQUE NOT NULL
-- );

-- CREATE TABLE users (
--     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     username VARCHAR(50) UNIQUE NOT NULL,
--     email VARCHAR(255) UNIQUE NOT NULL,
--     hashed_password TEXT NOT NULL,
--     school_id UUID REFERENCES schools(id),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- -- =====================
-- -- MUSIC ENTITIES
-- -- =====================

-- CREATE TABLE artists (
--     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     name VARCHAR(255) NOT NULL,
--     musicbrainz_id UUID UNIQUE VARCHAR(100) UNIQUE
-- );

-- CREATE TABLE albums (
--     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     name VARCHAR(255) NOT NULL,
--     artist_id UUID REFERENCES artists(id) ON DELETE CASCADE,
--     musicbrainz_id UUID UNIQUE VARCHAR(100) UNIQUE,
--     release_date DATE
-- );

-- CREATE TABLE songs (
--     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     name VARCHAR(255) NOT NULL,
--     artist_id UUID REFERENCES artists(id) ON DELETE CASCADE,
--     album_id UUID REFERENCES albums(id) ON DELETE SET NULL,
--     musicbrainz_id UUID UNIQUE VARCHAR(100) UNIQUE,
--     duration_ms INT
-- );

-- -- =====================
-- -- USER ↔ MUSIC RELATIONSHIPS
-- -- =====================

-- CREATE TABLE user_artists (
--     user_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     artist_id UUID REFERENCES artists(id) ON DELETE CASCADE,
--     play_count INT DEFAULT 0,
--     liked BOOLEAN DEFAULT FALSE,
--     PRIMARY KEY (user_id, artist_id)
-- );

-- CREATE TABLE user_albums (
--     user_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     album_id UUID REFERENCES albums(id) ON DELETE CASCADE,
--     play_count INT DEFAULT 0,
--     liked BOOLEAN DEFAULT FALSE,
--     PRIMARY KEY (user_id, album_id)
-- );

-- CREATE TABLE user_songs (
--     user_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     song_id UUID REFERENCES songs(id) ON DELETE CASCADE,
--     play_count INT DEFAULT 0,
--     liked BOOLEAN DEFAULT FALSE,
--     PRIMARY KEY (user_id, song_id)
-- );

-- -- =====================
-- -- SOCIAL GRAPH
-- -- =====================

-- CREATE TABLE follows (
--     follower_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     following_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (follower_id, following_id),
--     CHECK (follower_id <> following_id)
-- );

-- CREATE TABLE matches (
--     user_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     matched_user_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     score FLOAT NOT NULL,
--     computed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (user_id, matched_user_id),
--     CHECK (user_id <> matched_user_id)
-- );

-- -- =====================
-- -- MESSAGING
-- -- =====================

-- CREATE TABLE conversations (
--     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- CREATE TABLE conversation_members (
--     conversation_id UUID REFERENCES conversations(id) ON DELETE CASCADE,
--     user_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     PRIMARY KEY (conversation_id, user_id)
-- );

-- CREATE TABLE messages (
--     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     conversation_id UUID REFERENCES conversations(id) ON DELETE CASCADE,
--     sender_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     content TEXT NOT NULL,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- -- =====================
-- -- DISCUSSIONS & COMMENTS
-- -- =====================

-- CREATE TYPE topic_type_enum AS ENUM ('artist', 'album', 'song');

-- CREATE TABLE discussion_topics (
--     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     topic_type topic_type_enum NOT NULL,
--     topic_ref_id UUID NOT NULL
-- );

-- CREATE TABLE comments (
--     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     topic_id UUID REFERENCES discussion_topics(id) ON DELETE CASCADE,
--     user_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     content TEXT NOT NULL,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- CREATE TABLE comment_likes (
--     comment_id UUID REFERENCES comments(id) ON DELETE CASCADE,
--     user_id UUID REFERENCES users(id) ON DELETE CASCADE,
--     PRIMARY KEY (comment_id, user_id)
-- );
